import React, { Component } from 'react';
import { Button, Modal, Form, Table } from 'react-bootstrap';
import t1 from '../img/t-1.png';
import t2 from '../img/t-2.png';
import t3 from '../img/t-3.png';

import m1 from '../img/m-di.png';
import m2 from '../img/m-so.png';
import m3 from '../img/m-xss.png';
import m4 from '../img/m-sf.png';
import m5 from '../img/m-l.png';
import m_lock from '../img/m-lock.png';

class Manage extends Component {

    FLAG_NUM = 10;

    state={
        newFlag:false,
        username:'',
        flagDI:false,
        flagSO:false,
        flagL:false,
        flagXSS:false,
        flagSF:false,
        flags:[],
    }

    constructor(props) {
        super(props);
    }

    componentWillMount(){
        const token = this.props.getAuthToken();
            if(token!=''){
                const hex = atob(token.split(' ')[1]);
                this.setState({username: hex.split(':')[0]});

                fetch('/mgruser/'.concat(hex.split(':')[0]),{
                    headers: { 'Authorization': this.props.getAuthToken() },
                  })
                .then(response => response.json())
                .then(data => this.setState({flagDI:data.flag_di, flagL:data.flag_l, flagSF:data.flag_sf, flagSO:data.flag_so, flagXSS:data.flag_xss}));

                fetch('/flags/'.concat(hex.split(':')[0]),{
                    headers: { 'Authorization': this.props.getAuthToken() },
                  })
                .then(response => response.json())
                .then(flags => this.setState({ flags }));
            }
    }

    insertNewFlag(flag){
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', 'Authorization': this.props.getAuthToken() },
            body: JSON.stringify({ username:this.state.username,  flag})
        };
        fetch('/flag', requestOptions)
            .then(response => {
                if (response.ok){
                    alert('Flag is OK. Congratulations!')
                } else {
                    alert('Incorrect or duplicated flag. Try again please.')
                }
            });
        this.setState({newFlag: !this.state.newFlag});
    }

    render(){
        if (this.state.username==''){
            const token = this.props.getAuthToken();
            if(token!=''){
                const hex = atob(token.split(' ')[1]);
                this.setState({username: hex.split(':')[0]});

                fetch('/mgruser/'.concat(hex.split(':')[0]),{
                    headers: { 'Authorization': this.props.getAuthToken() },
                  })
                .then(response => response.json())
                .then(data => this.setState({flagDI:data.flag_di, flagL:data.flag_l, flagSF:data.flag_sf, flagSO:data.flag_so, flagXSS:data.flag_xss}));

                fetch('/flags/'.concat(hex.split(':')[0]),{
                    headers: { 'Authorization': this.props.getAuthToken() },
                  })
                .then(response => response.json())
                .then(flags => this.setState({ flags }));
            }
        }
        return(
            <div className="manage-screen">
                {/* <p></p> */}
                {
                    this.state && this.state.username==='admin' &&
                    <div>
                        <p>flag_l_ca554e12680a79810d41ed0adfe0404ce7c68b0c99065c50472c262c85d853b8</p>

                    <Button
                        className="logout-button"
                        variant="outline-danger"
                        onClick={() => {
                            this.props.handlerLogout();
                            this.props.context.history.push("/login");
                        }}
                    >
                            Logout
                        </Button>
                    </div>
                }
                {
                    this.state && this.state.username!=='admin' && this.props && this.props.isAuth() && !this.state.newFlag &&
                    <div className="mgr-screen">
                        <div className="buttons-top-right-mgr">
                            <Button className="app-button-mgr" variant="primary" onClick={() => this.props.context.history.push("/app")}>Go to vulnerable app</Button>
                            <br/>
                            <Button className="app-button-mgr" variant="primary" onClick={() => this.setState({newFlag: !this.state.newFlag})}>Check and insert flag</Button>
                        </div>
                        {
                            this.state && this.state.flags &&
                            <div className="presents">
                                <div className="row">
                                    <div className="col-sm">
                                        <div className="trophy">
                                        <h4>YOUR RANK</h4>
                                        {
                                            this.state.flags.length == this.FLAG_NUM &&
                                            <div>
                                                <img className="trophy_img" src={t1} alt="Trophy"/> 
                                                <p>You are a real hacker! Congratulations!</p>
                                            </div>
                                        }
                                        {
                                            this.state.flags.length > this.FLAG_NUM/2 && this.state.flags.length < this.FLAG_NUM &&
                                            <div>
                                                <img className="trophy_img" src={t2} alt="Trophy"/> 
                                                <p>You are a good web hacker but... You are not yet a master!</p>
                                            </div>
                                        }
                                        {
                                            this.state.flags.length <= this.FLAG_NUM/2 &&
                                            <div>
                                                <img className="trophy_img" src={t3} alt="Trophy"/> 
                                                <p>You don't discover many flags yet. Continue working and you will get the gold and silver trophy!</p>
                                            </div>
                                        }
                                        <br/>
                                        <h4>{this.state.flags.length} of {this.FLAG_NUM} flags discovered</h4>
                                        </div>
                                    </div>
                                    <div className="col-sm">
                                        <div className="medals">
                                            <h4>YOUR MEDAL STAND</h4>
                                                {this.state.flagDI ? <img className="medal_img" src={m1} alt="Medal"/> :  <img className="medal_img" src={m_lock} alt="Medal"/>}
                                                {this.state.flagSO ? <img className="medal_img" src={m2} alt="Medal"/> :  <img className="medal_img" src={m_lock} alt="Medal"/>}
                                                {this.state.flagXSS ? <img className="medal_img" src={m3} alt="Medal"/> :  <img className="medal_img" src={m_lock} alt="Medal"/>}
                                                <br/>
                                                {this.state.flagSF ? <img className="medal_img" src={m4} alt="Medal"/> :  <img className="medal_img" src={m_lock} alt="Medal"/>}
                                                {this.state.flagL ? <img className="medal_img" src={m5} alt="Medal"/> :  <img className="medal_img" src={m_lock} alt="Medal"/>}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        }
                        <div className="data-contain-manager">
                            <div className="flags-table">
                                <h2>Your last flags</h2>
                                <Table responsive striped bordered hover size="sm">
                                    <thead>
                                        <tr>
                                            <th>Flag</th>
                                            <th>Date</th>
                                            <th>Description</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state && this.state.flags &&
                                                this.state.flags.map( f =>{
                                                    return (
                                                        <tr key={f.flag}>
                                                            <td>{f.flag}</td>
                                                            <td>{f.date}</td>
                                                            <td>{f.description}</td>
                                                        </tr>
                                                    )
                                        })}
                                    </tbody>
                                </Table>
                            </div>
                            <Button
                                className="logout-button"
                                variant="outline-danger"
                                onClick={() => {
                                    this.props.handlerLogout();
                                    this.props.context.history.push("/login");
                                }}
                            >
                                Logout
                        </Button>
                        </div>
                    </div>
                }
                {
                    this.state && this.state.username!=='admin' && this.props && this.props.isAuth() && this.state.newFlag &&
                    <div className="newflag-screen">
                        <form onSubmit={e => { e.preventDefault(); }}>
                            <div className='form-group'>
                                <label htmlFor="flag">Flag</label>
                                <input type="text" className="form-control" name="flag" ref={(r) => {this.flag = r}} />
                            </div>

                            <Button variant="primary" 
                                onClick={() => this.insertNewFlag(this.flag.value)}
                            >
                                Check
                            </Button>
                        </form>
                    </div>
                }
            </div>
        )
    }

}

export default Manage;