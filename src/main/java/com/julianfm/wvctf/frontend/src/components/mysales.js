import React, { Component } from 'react';
import { Table, Button } from 'react-bootstrap';

class MySales extends Component {

    componentWillMount(){
        fetch('/sales?username=user1',{
            headers: { 'Authorization': this.props.getAuthToken() },
          })
        .then(response => response.json())
        .then(mysales => this.setState({ mysales }));

        fetch('/orders/check',{
            headers: { 'Authorization': this.props.getAuthToken() },
          })
          .then(response => response.json())
          .then(ordersCSRF => this.setState({ ordersCSRF }));
    }

    render(){
        return (
            <div className="orders-screen">
                <h1>My sales</h1>
                <Table responsive striped bordered hover size="sm">
                    <thead>
                        <tr>
                            <th>Name of product</th>
                            <th>ID of product</th>
                            <th>Price of product</th>
                            <th>Vendor</th>
                            <th>Buyer</th>
                            <th>Number of products</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state && this.state.mysales &&
                                this.state.mysales.map( ord =>{
                                    const uri = "/products/".concat(ord.product.id);
                                    return (
                                        <tr key={ord.id}>
                                            <td>{ord.product.name}</td>
                                            <td>{ord.product.id}</td>
                                            <td>{ord.product.price}</td>
                                            <td>{ord.product.vendor.username}</td>
                                            <td>{ord.users.username}</td>
                                            <td>{ord.count}</td>
                                        </tr>
                                    )
                        })}
                    </tbody>
                </Table>
                {
                    this.state && this.state.ordersCSRF && this.state.ordersCSRF.length > 0 &&
                        <p>You exploit well the CSRF to user2 of order a product using commentaries: flag_sf_27055dd23f7d8f8568cf0083e3b5925f257cfee9d16a5627fe76ddb16b917009</p>
                }
            </div>
        );
    }

}

export default MySales;