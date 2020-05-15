import React, { Component } from 'react';
import {Table} from 'react-bootstrap';

class Products extends Component {

  componentWillMount () {
    const name = this.props.location.search.split("name=")[1];
    fetch('/products/search/'.concat(name))
      .then(response => response.json())
      .then(products => this.setState({ products }));
  }

  render () {
    return(
        <div className="products-screen">
            <h1>Products</h1>
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Color</th>
                        <th>Category</th>
                        <th>Size</th>
                        <th>Price</th>
                        <th>Vendor</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        this.state &&
                            this.state.products.map( prod =>{
                                const uri = "/products/".concat(prod.id);
                                return (
                                    <tr onClick={() => this.props.history.push(uri)} key={prod.id}>
                                        <td>{prod.id}</td>
                                        <td>{prod.name}</td>
                                        <td>{prod.description}</td>
                                        <td>{prod.color}</td>
                                        <td>{prod.category.name}</td>
                                        <td>{prod.size}</td>
                                        <td>{prod.price}</td>
                                        <td>{prod.vendor.username}</td>
                                    </tr>
                                )
                    })}
                </tbody>
            </Table>
        </div>
    )
  }
}

export default Products;