import axios from "axios";

export function adminLogin(username, password) {
    return axios({
        url: '/login/admin',
        method: 'POST',
        params: {
            username: username,
            password: password
        }
    })
}

export function studentLogin(username, password) {
    return axios({
        url: '/login/student',
        method: 'POST',
        params: {
            username: username,
            password: password
        }
    })
}

export function teacherLogin(username, password) {
    return axios({
        url: '/login/teacher',
        method: 'POST',
        params: {
            username: username,
            password: password
        }
    })
}