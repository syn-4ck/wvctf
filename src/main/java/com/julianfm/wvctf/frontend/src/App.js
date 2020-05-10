import React, { Component } from 'react';
import Products from './components/products';
import Product from './components/product';
import Index from './components/index';
import {Navbar, Nav} from 'react-bootstrap';
import {BrowserRouter, Switch, Route} from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

class App extends Component {

  componentWillMount () {
  }

  render () {
    return(
      <div>
        {/* <Container> */}
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
          <Navbar.Brand href="/">VendeCompraRopa</Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">
            </Nav>
            <Nav>
              <Nav.Link href="#deets">My space</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
          <BrowserRouter>
            {/* <Redirect 
              from="/"
              to="/products" /> */}
            <Switch>
            <Route 
                exact
                path="/"
                component={Index}/>
            <Route 
                exact
                path="/products/:id"
                component={Product}/>
              <Route 
                exact
                path="/products"
                component={Products}/>
            </Switch>
          </BrowserRouter>
        {/* </Container> */}
      </div>
    )
  }
}

export default App;
