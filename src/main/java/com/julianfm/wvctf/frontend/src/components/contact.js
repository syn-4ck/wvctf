import React, { Component } from 'react';
import { Button, Form, FormControl, FormGroup} from 'react-bootstrap';
import InnerHTML from 'dangerously-set-html-content';

class Contact extends Component {

    componentWillMount(){

    }

    componentDidMoun(){
    }

    showFlag(name){
        var div = document.createElement('div');
        div.innerHTML = name;
        var scripts = div.getElementsByTagName('script');
        var i = scripts.length;
        if (i>0){
            alert('flag_xss_f655f2a8f72c55b96e90ea538fa59e9b34e16f1bb7971027e078d045772fee2e');
        }
    }

    findUserContacts(name){
        if (name!=""){
            const xss = require("xss");
            const html = xss(name);
            if (name==html){
                fetch('/contact/'.concat(name),{
                    headers: { 'Authorization': this.props.getAuthToken() },
                  })
                .then(response => response.json())
                .then(contacts => this.setState({ contacts }));
            } else {
                fetch('/contact/user1',{
                    headers: { 'Authorization': this.props.getAuthToken() },
                  })
                .then(response => response.json())
                .then(contacts => this.setState({ contacts }));

                this.showFlag(name);
            }
        }
    }

    render(){
        return (
            <div className="contact-screen">
                    <Form inline onSubmit={e => { e.preventDefault(); }} className="search-form-input">
                        <FormGroup>
                        <FormControl type="text" placeholder="Search a user name..." ref={(r) => {this.input = r}}/>
                            <Button
                                onClick={ () => {
                                   this.findUserContacts(this.input.value);
                                }}
                            >
                                Search
                            </Button>
                        </FormGroup>
                    </Form>
                    {
                        this.state && this.state.contacts &&
                            <div className="contact-data">
                                <InnerHTML html={`<h5>Search contact of ${this.input.value}: </h5>`} />
                            </div>
                    }
                    {
                        this.state && this.state.contacts &&
                            this.state.contacts.map( c =>{
                                return (
                                    <div className="contact-data">
                                        <p>Phone number: {c.phoneNumber}</p>
                                        <p>Mail: {c.mail}</p>
                                    </div>
                                )
                            })
                    }
            </div>
        );
    }

}

export default Contact;