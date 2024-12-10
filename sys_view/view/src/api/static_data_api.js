import axios from "axios";
export function GetTitleType(){
    return axios({
        url: '/utils/get_title_type',
        method: 'GET'
    })
}
export function GetSchoolYearList(){
    return axios({
        url: '/utils/get_schoolyear_list',
        method: 'GET'
    })
}
export function GetTeacherTitleList(){
    return axios({
        url: '/utils/get_title_list',
        method: 'GET'
    })
}
export function GetEducationList(){
    return axios({
        url: '/utils/get_education_list',
        method: 'GET'
    })
}
export function GetSelectDeadline(){
    return axios({
        url: '/utils/get_select_deadline',
        method: 'GET'
    })
}
