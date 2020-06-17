import React, { Component } from 'react';
import { Button } from 'react-bootstrap';

class User extends Component {

    resetDB(){
        fetch('/reset',{
            headers: { 'Authorization': this.props.getAuthToken() },
          }).then(response => {
            if (response.ok){
                alert('Your database is reset')
            } else {
                alert('You cannot reset the database')
            }
          })
    }

    render(){
        return(
            <div className="user-data">
                <p>You are the user "user1" in this vulnerable app. Find all flags, good luck!</p>
                <Button className="user-data-button" variant="outline-danger" onClick={() => this.resetDB()}>Reset database</Button>
            </div>
        )
    }

}

export default User;