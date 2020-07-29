import React, { Component } from 'react';
import { Button, Modal, Form, Table } from 'react-bootstrap';
import logo from '../img/logo.png';

class Login extends Component {

    state={
        createVisibleLogin:false,
    }

    constructor(props) {
        super(props);
    }

    componentWillMount(){

    }

    handleOnClick(visible){
        this.setState({createVisibleLogin:!visible});
    }

    login(username, password){
        const cred = username+':'+password;
        fetch(`/credentials?username=${username}&password=${password}`)
        .then(response => response.json())
        .then(data => {
              if (!data.username){
                alert("Username or password incorrect! Please, try again...");
              } else {
                this.props.handlerLogin(cred);
                this.props.context.history.push("/");
              }
        });
    }

    register(username, password, email){
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Authorization': this.props.getAuthToken() },
            body: JSON.stringify({ username, password, email })
        };
        fetch('/mgruser', requestOptions)
            .then(response => {
                if (response.ok){
                    alert('User created. Please login in')
                } else {
                    alert('Ups, any problem ocurred, sorry')
                }
            });

            this.setState({createVisibleLogin:!this.state.createVisibleLogin});
    }

    render(){
        return(
            <div className="manage-screen">
                <img className="logo" src={logo} alt="Logo"/>
                {
                    this.props && !this.props.isAuth() && !this.state.createVisibleLogin &&
                    <div className="login-screen">
                        <h2>Login</h2>
                    <form onSubmit={e => { e.preventDefault(); }}>
                        <div className='form-group'>
                            <label htmlFor="username">Username</label>
                            <input type="text" className="form-control" name="username" ref={(r) => {this.username = r}} />
                        </div>
                        <div className='form-group'>
                            <label htmlFor="password">Password</label>
                            <input type="password" className="form-control" name="password" ref={(r) => {this.password = r}} />
                        </div>
                        <Button variant="primary" 
                            onClick={() => this.login(this.username.value, this.password.value)}
                        >
                            Login
                        </Button>
                    </form>
                    <div className="register">
                            <p>If you are not already registered please click <a onClick={() => this.handleOnClick(this.state.createVisibleLogin)}>here</a> </p>
                        </div>
                        </div>
                }
                {
                    this.props && !this.props.isAuth() && this.state.createVisibleLogin &&
                    <Modal.Dialog className="modal-class">
                        <Modal.Header>
                            <Modal.Title>Register</Modal.Title>
                        </Modal.Header>

                        <Modal.Body>
                            <form>
                                <p>Username:</p>
                                <input type="text" required name="username" ref={(r) => {this.formName = r}}/>
                                <p>Password:</p>
                                <input type="password" required pattern=".{8,}" name="password" ref={(r) => {this.formPass= r}}/>
                                <p>Email:</p>
                                <input type="email" required name="email" ref={(r) => {this.formMAil = r}}/>
                            </form>

                        </Modal.Body>

                        <Modal.Footer>
                            <Button variant="secondary" onClick={() => this.handleOnClick(this.state.createVisibleLogin)}>Close</Button>
                            <Button variant="primary" onClick={() => this.register(this.formName.value, this.formPass.value, this.formMAil.value)}>Submit</Button>
                        </Modal.Footer>
                    </Modal.Dialog>
            }
            {
                this.props && this.props.isAuth() &&
                    <Button variant="primary" onClick={() => this.props.context.history.push("/")}>Continue</Button>

            }
            </div>
        )
    }

}

export default Login;