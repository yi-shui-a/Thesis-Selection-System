import axios from "axios";
export function GetStudentInfo(sno){
    return axios({
        url: '/student/get_myinfo',
        method: 'GET',
        params:{
            sno: sno
        }
    })
}
export function ResetPassword(sno,password){
    return axios({
        url: '/student/update_password',
        method: 'POST',
        params:{
            sno: sno,
            password: password
        }
    })
}
export function EditStudentInfo(data){
    return axios({
        url: '/student/update_myinfo',
        method: 'POST',
        data: data
    })
}
export function GetAcceptPaperBySno(sno){
    return axios({
        url: '/student/get_mypaper',
        method: 'GET',
        params:{
            sno: sno,
            acceptStatus: 'ACCEPT'
        }
    })
}
export function GetPaperBySno(sno,acceptStatus){
    return axios({
        url: '/student/get_mypaper',
        method: 'GET',
        params:{
            sno: sno,
            acceptStatus: acceptStatus
        }
    })
}
export function CancelPaper(sno,pno){
    return axios({
        url: '/student/cancel_paper',
        method: 'POST',
        params:{
            sno: sno,
            pno: pno
        }
    })
}
export function GetAblePaper(sno){
    return axios({
        url: '/student/get_able_paper',
        method: 'GET',
        params:{
            sno: sno
        }
    })
}
export function SelectPaper(sno,pno){
    return axios({
        url: '/student/select_paper',
        method: 'POST',
        params:{
            sno: sno,
            pno: pno
        }
    })
}
export function ReSelectPaper(sno,pno){
    return axios({
        url: '/student/reselect_paper',
        method: 'POST',
        params:{
            sno: sno,
            pno: pno
        }
    })
}
