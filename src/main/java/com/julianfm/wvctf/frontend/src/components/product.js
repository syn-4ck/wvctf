import React, { Component } from 'react';
import { Button, Form } from 'react-bootstrap';
import InnerHTML from 'dangerously-set-html-content';

class Product extends Component {

  componentWillMount () {
    fetch(this.props.history.location.pathname)
      .then(response => response.json())
      .then(currentProduct => this.setState({ currentProduct }));

    const idProd = this.props.history.location.pathname.split("/products/")[1];
    fetch("/commentary/".concat(idProd))
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

    const textValidated = this.stripScripts(text);

    if (textValidated){
      const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({users: {"username": "user1"}, product: {"id": this.state.currentProduct.id}, text: textValidated })
      };
      fetch('/commentary', requestOptions)
      .then(response => {if(response.ok){ alert('Comment published! Please update the page to see it');}});
    }
    this.update = true;
  }

  render () {
    if (this.image && this.state && this.state.currentProduct){
      fetch("/image/".concat(this.state.currentProduct.name))
      .then(response => response.blob())
      .then(data => this.setState({ ...this.state, imageData: URL.createObjectURL(data) }));
      this.image=false;
    }
    if(this.xss && this.state && this.state.commentaryList){
      const xss = require("xss");
      this.state.commentaryList.map( c =>{
        const html = xss(c.text);
        if (c.text!==html){
          console.log('flag_08f0ee3843dd031f48a1fa14045b5faa9c1f38eb21ac941479421da5c99afafa');
        };
      });
      this.xss=false;
    }
    return(
      <div ref={this.element}>
          <Button onClick={() => this.props.history.goBack() }>Volver atrás</Button>
          {this.state && this.state.currentProduct &&
            <div>
                <div>
                    <h1>{this.state.currentProduct.name}</h1>
                    <h3>Precio: {this.state.currentProduct.price} €</h3>
                <h5>{this.state.currentProduct.description}</h5>
                <img src={this.state.imageData} alt="Product image"/>
                </div>
                <h4>Product characteristics:</h4>
                <h6>Size: {this.state.currentProduct.size}</h6>
                <h6>Color: {this.state.currentProduct.color}</h6>
                <h6>Category: {this.state.currentProduct.category.name}</h6>
                <h6>Vendor: {this.state.currentProduct.vendor.username}</h6>
                <br/>
                <h5>Commentaries:</h5>
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
                          <InnerHTML html={`<h5><b>${c.users.username}</b>: ${c.text}</h5>`} />
                        );
                    } )
                }
            </div>
          }
      </div>
    )
  }
}

export default Product;