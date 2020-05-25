import React, { Component } from 'react';
import {Form, FormControl, FormGroup, Button, Modal, Dropdown} from 'react-bootstrap';

class Index extends Component {

    state = {
        products: [],
        currentProduct: {},
        categories: [],
        createVisible: false,
        categoryValue: "Category",
        commentaryList: [],
        orders: [],
        contacts: [],
    }

  componentWillMount () {
    fetch('/category')
      .then(response => response.json())
      .then(categories => this.setState({ categories }));
  }

  handleOnClick(createVisible){
    this.setState({...this.state, createVisible: !createVisible});
  }

  handleOnClickDropdown(categoryValue){
    this.setState({...this.state, categoryValue});
  }

  submitForm(name, description, size, color, price, category, createVisible, image){

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, description, size, color, price, category:{"name":category}, vendor: {"username":"user1"} })
    };
    fetch('/products', requestOptions)
        .then(response => {
            if (response.ok){
                alert('Product created. Please update the page to see it')
            } else {
                alert('Ups, any problem ocurred, sorry')
            }
          });


    //Image
    const formData = new FormData();
    formData.append('file', image.files[0]);

    const requestOptionsImg = {
        method: 'POST',
        headers: {},
        body: formData
    };
    fetch(`/upload?name=${name}`, requestOptionsImg)
        .then(response => {if(!response.ok){ alert('Product created but image not uploaded');}});
    

    this.setState({...this.state, createVisible: !createVisible});
  }

  render () {
    return(
      <div>
            {
                this.state && this.state.createVisible &&
                    <Modal.Dialog className="modal-class">
                        <Modal.Header>
                            <Modal.Title>Create new product to sale</Modal.Title>
                        </Modal.Header>

                        <Modal.Body>
                            <Form>
                                <Form.Group controlId="formBasicName" required>
                                    <Form.Label>Name*</Form.Label>
                                    <Form.Control type="text" required placeholder="Enter name of product" ref={(r) => {this.formName = r}}/>
                                </Form.Group>

                                <Form.Group controlId="formBasicDesc">
                                    <Form.Label>Description</Form.Label>
                                    <Form.Control type="text" placeholder="Enter description of product" ref={(r) => {this.formDesc = r}}/>
                                </Form.Group>

                                <Form.Group controlId="formBasicSize">
                                    <Form.Label>Size* (max value 5 chars)</Form.Label>
                                    <Form.Control type="text" maxLength="5" required placeholder="Enter size of product" ref={(r) => {this.formSize = r}}/>
                                </Form.Group>

                                <Form.Group controlId="formBasicColor">
                                    <Form.Label>Color</Form.Label>
                                    <Form.Control type="text" placeholder="Enter color of product" ref={(r) => {this.formColor = r}}/>
                                </Form.Group>

                                <Form.Group controlId="formBasicPrice">
                                    <Form.Label>Price* (decimal number)</Form.Label>
                                    <Form.Control type="number" required step=".01" placeholder="Enter price of product" ref={(r) => {this.formPrice = r}}/>
                                </Form.Group>

                                <Form.Group controlId="formBasicImage">
                                    <Form.Label>Image</Form.Label>
                                    <Form.Control
                                        type='file'
                                        name="input-file"
                                        label='Image'
                                        ref={(r) => {this.formImage = r}}
                                    />
                                </Form.Group>
                            </Form>
                        <Dropdown>
                            <Dropdown.Toggle id="dropdown-category">
                                {this.state.categoryValue}
                            </Dropdown.Toggle>

                            <Dropdown.Menu>
                                {
                                    this.state && this.state.categories &&
                                        this.state.categories.map( c => {
                                            return (
                                                <Dropdown.Item key={c.name} onSelect={() => this.handleOnClickDropdown(c.name)}>{c.name}</Dropdown.Item>
                                            );
                                        } )
                                }
                            </Dropdown.Menu>
                        </Dropdown>

                        </Modal.Body>

                        <Modal.Footer>
                            <Button variant="secondary" onClick={() => this.handleOnClick(this.state.createVisible)}>Close</Button>
                            <Button variant="primary" onClick={() => this.submitForm(this.formName.value, this.formDesc.value, this.formSize.value, 
                                this.formColor.value, this.formPrice.value, this.state.categoryValue, this.state.createVisible, this.formImage)}>Submit</Button>
                        </Modal.Footer>
                    </Modal.Dialog>
            }
            {
                this.state && !this.state.createVisible &&
                <div className="index-code">
                    <h2>Find products</h2>
                    <Form inline className="search-form-input">
                        <FormGroup role="form">
                            <FormControl type="text" placeholder="Search a product name..." className="form-control" ref={(r) => {this.input = r}}/>
                            <Button
                                className="btn btn-primary btn-large centerButton" 
                                type="submit"
                                onClick={ () => {
                                    this.props.history.push("/products?name=".concat(this.input.value));
                                }}
                            >
                                Search
                            </Button>
                        </FormGroup>
                    </Form>
                    <div className="container">
                        <div className="row">
                            <div className="col-sm">
                                <h3>Sale your products:</h3>
                                <br/>
                                <p>Here you can sale your own products. Please, click on "New product" to publish the article and send the form.</p>
                                <br/>
                                <Button onClick={() => this.handleOnClick(this.state.createVisible)}>New product</Button>
                            </div>
                            <div className="col-sm">
                                <h3>Your orders:</h3>
                                <br/>
                                <p>Here you can see your ordered products. Please, click over your "Show orders" to see it.</p>
                                <br/>
                                <Button onClick={() => this.props.history.push("/orders?username=user1")}>Show orders</Button>
                                <br/>
                                <Button onClick={() => this.props.history.push("/contact")} className="contact-button">Get vendor contact</Button>
                            </div>
                        </div>
                    </div>
                </div>
        }
      </div>
    )
  }
}

export default Index;