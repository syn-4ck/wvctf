import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const PrivateRoute = ({ authenticated,login,logout, component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        authenticated
            ? <Component {...props} />
            : <Redirect to={{ pathname: '/', state: { from: props.location } }} />
    )} />
)

export default PrivateRoute;