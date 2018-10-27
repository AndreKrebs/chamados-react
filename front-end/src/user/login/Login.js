import React, { Component } from "react";
import { request } from '../../util/APIUtils';

class Login extends Component {

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        console.log(data);

        request({
            url: request.API_BASE_URL + "/auth/signin",
            method: 'POST',
            body: JSON.stringify(data)
        });

    }

    render() {

        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>User/E-mail:</label><br />
                    <input type="text" name="login" />
                    <br />
                    <br />
                    
                    <label>Password:</label><br />
                    <input type="password" name="password" />
                    <br />
                    <br />

                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }
}

export default Login;