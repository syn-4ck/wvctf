import React, { Component } from 'react';
import { Table, Button } from 'react-bootstrap';
import InnerHTML from 'dangerously-set-html-content';

class Sales extends Component {

    componentWillMount(){
        fetch('/products/vendor/user1',{
            headers: { 'Authorization': this.props.getAuthToken() },
          })
        .then(response => response.json())
        .then(sales => this.setState({ sales }));
    }

    render(){
        return (
            <div className="orders-screen"> 
                <InnerHTML html="<!--You shouldn't leave commentaries of code or relevant information: flag_l_b287569d94db9e49416b0dec7e591e4b1dfcf3cfa76af5dce9be0356c2fd66a9-->" />
                <h1>
                    Sales
                </h1>
                <Table responsive striped bordered hover size="sm">
                    <thead>
                        <tr>
                            <th>Name of product</th>
                            <th>ID of product</th>
                            <th>Price of product</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state && this.state.sales &&
                                this.state.sales.map( s =>{
                                    const uri = "/products/".concat(s.id);
                                    return (
                                        <tr key={s.id}>
                                            <td>{s.name}</td>
                                            <td>{s.id}</td>
                                            <td>{s.price}</td>
                                        </tr>
                                    )
                        })}
                    </tbody>
                </Table>
            </div>
        );
    }

}

export default Sales;