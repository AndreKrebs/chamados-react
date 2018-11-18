import { ACCESS_TOKEN, API_BASE_URL } from '../constants';

export function request (options) {
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    }
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
        
    }

    return fetch(API_BASE_URL+options.url, {
        method: options.method,
        headers: headers,
        /*headers: {
        'Content-Type': 'application/json'
        },*/
        body: options.body
    });
    /*
    .then(function(response){ 
        console.log(response.headers.values());
        if(!response.ok) {
            return Promise.reject(response);
        }
        return response;
    })
    .then(function(data){
        console.log("")
    });*/
};

export function signup(signupRequest) {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}
