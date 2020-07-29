import React, { Component } from 'react';
import { Button, Form, Table } from 'react-bootstrap';
import InnerHTML from 'dangerously-set-html-content';
import { TiArrowBack } from 'react-icons/ti';
import { RiCoinsLine } from 'react-icons/ri';
import { IconContext } from "react-icons";

class Product extends Component {

  componentWillMount () {
    const idProd = this.props.context.history.location.pathname.split("/products/")[1];

    fetch("/products/".concat(idProd),{
      headers: { 'Authorization': this.props.getAuthToken() },
    })
      .then(response => response.json())
      .then(currentProduct => this.setState({ currentProduct }));

    fetch("/commentary/".concat(idProd),{
      headers: { 'Authorization': this.props.getAuthToken() },
    })
        .then(response => response.json())
        .then(commentaryList => this.setState({ commentaryList }));

    this.update = false;
    this.image = true;
    this.xss = true;
  }

  componentWillUnmount() {
  }

  stripScripts(s) {
    var div = document.createElement('div');
    div.innerHTML = s;
    var scripts = div.getElementsByTagName('script');
    var i = scripts.length;
    while (i--) {
      scripts[i].parentNode.removeChild(scripts[i]);
    }
    return div.innerHTML;
  }

  createComment(text){

    if (text.includes("/orders?user=user1&product=")){
      this.props.setCSRF();
    }

    const textValidated = this.stripScripts(text);

    if (textValidated){
      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json', 'Authorization': this.props.getAuthToken() },
          body: JSON.stringify({users: {"username": "user1"}, product: {"id": this.state.currentProduct.id}, text: textValidated })
      };
      fetch('/commentary', requestOptions)
      .then(response => {
        if (response.ok){
            alert('Commentary published. Please update the page to see it')
        } else {
            alert('Ups, any problem ocurred, sorry')
        }
      })
    }
    this.update = true;
  }

  order(id){
    fetch('/orders?user=user1&product='.concat(id),{
      headers: { 'Authorization': this.props.getAuthToken() },
    })
    .then(response => {
      if (response.ok){
          alert('Order processed. Please update the page to see it')
      } else {
          alert('Ups, any problem ocurred, sorry')
      }
    })
  }

  render () {
    if (this.image && this.state && this.state.currentProduct){
      fetch("/image/".concat(this.state.currentProduct.name),{
        headers: { 'Authorization': this.props.getAuthToken() },
      })
      .then(response => response.blob())
      .then(data => this.setState({ ...this.state, imageData: URL.createObjectURL(data) }));
      this.image=false;
    }
    if(this.xss && this.state && this.state.commentaryList){
      const xss = require("xss");
      this.state.commentaryList.map( c =>{
        const html = xss(c.text);
        if (c.text!==html){
          console.log('flag_xss_08f0ee3843dd031f48a1fa14045b5faa9c1f38eb21ac941479421da5c99afafa');
        };
      });
      this.xss=false;
    }
    return(
      <div ref={this.element} className="product-screen">
          <Button className="back-button" onClick={() => this.props.context.history.goBack() }><IconContext.Provider value={{ size:"2em" }}><TiArrowBack/></IconContext.Provider></Button>
          {this.state && this.state.currentProduct &&
            <div>
              <div className="header-data">
                <div className="principal-data">
                  <div className="text-principal-data">
                    <div>
                    <div className="prod-title">
                      <h1>{this.state.currentProduct.name}</h1>
                    </div>
                    <h5>{this.state.currentProduct.description}</h5>
                    </div>
                  </div>
                </div>
                <div className="image-container">
                  <img src={this.state.imageData} alt="Product image" className="product-image"/>
                </div>
              </div>
              <div className="characteristics">
                  <h4>Product characteristics:</h4>
                  <br/>
                  <Table bordered size="sm" className="chars-table">
                    <tbody>
                      <tr key="size">
                        <td><b>Size</b></td>
                        <td>{this.state.currentProduct.size}</td>
                      </tr>
                    </tbody>
                    <tbody>
                      <tr key="color">
                        <td><b>Color</b></td>
                        <td>{this.state.currentProduct.color}</td>
                      </tr>
                    </tbody>
                    <tbody>
                      <tr key="category">
                        <td><b>Category</b></td>
                        <td>{this.state.currentProduct.category.name}</td>
                      </tr>
                    </tbody>
                    <tbody>
                      <tr key="vendor">
                        <td><b>Vendor</b></td>
                        <td>{this.state.currentProduct.vendor.username}</td>
                      </tr>
                    </tbody>
                  </Table>
                  <p>Another users conected: user2</p>
                </div>
                <div className="order-container">
                  <div className="prod-price">
                      <h3><RiCoinsLine/> {this.state.currentProduct.price} €</h3> 
                      <Button variant="primary" onClick={() => this.order(this.state.currentProduct.id)}>Order</Button>
                  </div>
                </div>
                <div className="comment-container">
                  <br/>
                  <h5><b>Commentaries:</b></h5>
                  <Form>
                    <Form.Group controlId="formBasicName">
                      <Form.Label></Form.Label>
                      <Form.Control type="text" required placeholder="Enter your product commentary" ref={(r) => {this.comment = r}}/>
                    </Form.Group>
                    <Button variant="primary" onClick={() => this.createComment(this.comment.value)}>Submit</Button>
                  </Form>
                  {
                    this.state.commentaryList &&
                        this.state.commentaryList.map( c => {
                          return (
                            <div className="comment">
                            <InnerHTML html={`<h5><b>${c.users.username}</b>: ${c.text}</h5>`} />
                            </div>
                          );
                      } )
                  }
                </div>
            </div>
          }
      </div>
    )
  }
}

export default Product;