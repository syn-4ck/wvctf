import React, { Component } from 'react';
import Products from './components/products';
import Product from './components/product';
import Contact from './components/contact';
import User from './components/user';
import Index from './components/index';
import {Navbar, Nav} from 'react-bootstrap';
import {BrowserRouter, Switch, Route} from 'react-router-dom';
import { FaUserCircle } from "react-icons/fa";

import campus from './img/logo_campus.png';
import ucam from './img/logo_ucam.png';
import telefonica from './img/logo_telefonica.png';
import elevenpaths from './img/logo_elevenpaths.png';
import excellence from './img/logo_excellenceInnova.png';

import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Orders from './components/orders';

class App extends Component {

  componentWillMount () {
  }

  render () {
    return(
      <div className="main">
        {/* <Container> */}
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
          <Navbar.Brand href="/">VendeCompraRopa</Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="mr-auto">
            </Nav>
            <Nav>
              <Nav.Link href="/user"><FaUserCircle/> user1</Nav.Link>
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
            <Route 
                exact
                path="/orders"
                component={Orders}/>
            <Route 
                exact
                path="/contact"
                component={Contact}/>
            <Route 
                exact
                path="/user"
                component={User}/>
            </Switch>
          </BrowserRouter>
          <Navbar className="footer" expand="lg" bg="dark" variant="dark">
            <div class="row">
              <div class="column">
                <img className="footer_img" src={campus} alt="Logo Campus Ciberseguridad"/>
              </div>
              <div class="column">
                <img className="footer_img" src={ucam} alt="UCAM"/>
              </div>
              <div class="column">
                <img className="footer_img" src={telefonica} alt="Telefónica"/>
              </div>
              <div class="column">
                <img className="footer_img" src={elevenpaths} alt="ElevenPaths"/>
              </div>
              <div class="column">
                <img className="footer_img" src={excellence} alt="Excellence Innova"/>
              </div>
            </div> 
          </Navbar>
        {/* </Container> */}
      </div>
    )
  }
}

export default App;
