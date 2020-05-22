import React, { Component } from 'react';
import { Button, Form, FormControl, FormGroup} from 'react-bootstrap';
//import InnerHTML from 'dangerously-set-html-content';

class Contact extends Component {

    componentWillMount(){

    }

    componentDidMoun(){
    }

    findUserContacts(name){
        fetch('/contact/'.concat(name))
        .then(response => response.json())
        .then(contact => this.setState({ contact }));

    }

    render(){
        return (
            <div className="contact-screen">
                    <Form inline className="search-form-input">
                        <FormGroup role="form">
                        <FormControl type="text" placeholder="Search a user name..." className="form-control" ref={(r) => {this.input = r}}/>
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
                        this.state && this.state.contact &&
                            <div className="contact-data">
                                <p>Phone number: {this.state.contact.phoneNumber}</p>
                                <p>Mail: {this.state.contact.mail}</p>
                            </div>
                    }
            </div>
        );
    }

}

export default Contact;