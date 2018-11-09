import React, { Component } from "react";
import { request } from '../../util/APIUtils';


class Login extends Component {

    constructor() {
        super();

        this.submitForm = this.submitForm.bind(this);

        this.state = {
            username:"",
            password:""
        };

        this.onChange = (event) => {
            const state = Object.assign({}, this.state);
            const campo = event.target.name;
            state[campo] = event.target.value;
            this.setState(state);
        };
    }

    submitForm() {
        const data = Object.assign({}, this.state)

        request({
            url: "/login",
            method: 'POST',
            body: JSON.stringify(data)
        })
        // .then();

    }

    render() {

        return (
            <div>
                    <label>User/E-mail:</label><br />
                    <input type="text" value={this.state.username} name="username" onChange={this.onChange}/>
                    <br />
                    <br />
                    
                    <label>Password:</label><br />
                    <input type="password" value={this.state.password} name="password" onChange={this.onChange}/>
                    <br />
                    <br />
                {JSON.stringify(this.state)}
                <button onClick={this.submitForm}>Submit</button>
            </div>
        );
    }
}

export default Login;