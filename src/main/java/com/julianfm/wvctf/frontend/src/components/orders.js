import React, { Component } from 'react';
import { Table, Button } from 'react-bootstrap';

class Orders extends Component {

    componentWillMount(){
        const username = this.props.location.search.split("username=")[1];
        fetch('/orders/list?username='.concat(username))
        .then(response => response.json())
        .then(orders => this.setState({ orders }));
    }

    cancelOrder (id){
        fetch('/orders/'.concat(id), {
            method: 'DELETE',
          }).then(response => {
            if (response.ok){
                alert('Order canceled. Please update the page to see it')
            } else {
                alert('Ups, any problem ocurred, sorry')
            }
          })
    }

    render(){
        return (
            <div className="orders-screen">
                <h1>Orders</h1>
                <Table responsive striped bordered hover size="sm">
                    <thead>
                        <tr>
                            <th>Name of product</th>
                            <th>ID of product</th>
                            <th>Price of product</th>
                            <th>Vendor</th>
                            <th>Number of products</th>
                            <th>Cancel order</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state && this.state.orders &&
                                this.state.orders.map( ord =>{
                                    const uri = "/products/".concat(ord.product.id);
                                    return (
                                        <tr key={ord.id}>
                                            <td>{ord.product.name}</td>
                                            <td>{ord.product.id}</td>
                                            <td>{ord.product.price}</td>
                                            <td>{ord.users.username}</td>
                                            <td>{ord.count}</td>
                                            <Button variant="outline-danger" onClick={() => this.cancelOrder(ord.id)}>Cancel order</Button>
                                        </tr>
                                    )
                        })}
                    </tbody>
                </Table>
            </div>
        );
    }

}

export default Orders;