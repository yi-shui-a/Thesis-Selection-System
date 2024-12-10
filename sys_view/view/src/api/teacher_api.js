import axios from "axios";

export function getTeacherInfo(tno) {
  return axios({
    url: '/teacher/get_myinfo',
    method: 'GET',
    params:{
        tno: tno
    }
  })
}
export function GetTeacherTeachNum(tno){
    return axios({
        url: '/utils/get_teacher_student_list',
        method: 'GET',
        params:{
            tno: tno
        }
    })
}
export function CreatePaper(tno,data){
    return axios({
        url: '/teacher/create_paper',
        method: 'POST',
        params:{
            tno: tno,
            title: data.title,
            type: data.type,
            schoolyear: data.schoolyear,
            description: data.description,
        },

    })
}
export function EditPaper(paper){
    return axios({
        url: '/teacher/update_paper',
        method: 'POST',
        data: paper
    })
}
export function DeletePaper(pno){
    return axios({
        url: '/teacher/delete_paper',
        method: 'POST',
        params:{
            pno: pno
        }
    })
}
export function GetAllPaperByTno(tno){
    return axios({
        url: '/teacher/get_paper',
        method: 'GET',
        params:{
        tno: tno
        }
    })
}
export function GetPaperByPno(pno){
    return axios({
        url: '/utils/get_paper_by_pno',
        method: 'GET',
        params:{
            pno: pno
        }
    })
}
export function GetPaperHistoryByTno(tno,queryData){
    return axios({
        url: '/teacher/get_history_paper',
        method: 'GET',
        params:{
            tno: tno,
            title: queryData.title,
            type: queryData.type,
            schoolyear: queryData.schoolyear,
            studentName: queryData.studentName,
            acceptStatus: queryData.acceptStatus
        }
    })
}
export function EditTeacherInfo(data){
    return axios({
        url: '/teacher/update_myinfo',
        method: 'POST',
        data: data
    })
}
export function ResetPassword(tno,password){
    return axios({
        url: '/teacher/update_password',
        method: 'POST',
        params:{
            tno: tno,
            password: password
        }
    })
}
export function TeacherGetAllPaper(tno,queryData){
    return axios({
        url: '/teacher/get_paper',
        method: 'GET',
        params:{
            tno: tno,
            title: queryData.title,
            type: queryData.type,
            schoolyear: queryData.schoolyear,
            selectStatus: queryData.selectStatus,
            checkStatus: queryData.checkStatus,
            acceptStatus: queryData.acceptStatus
        }
    })
}
export function GetPaperNeedCheck(tno,queryData){
    return axios({
        url: '/teacher/get_need_check_paper',
        method: 'GET',
        params:{
            tno: tno,
            title: queryData.title,
            type: queryData.type,
            schoolyear: queryData.schoolyear,
            studentName: queryData.studentName,
            selectStatus: queryData.selectStatus
        }
    })
}
export function AcceptPaper(data){
    return axios({
        url: '/teacher/accept_student',
        method: 'POST',
        data: data
    })
}
export function RefusePaper(data){
    return axios({
        url: '/teacher/refuse_student',
        method: 'POST',
        data: data
    })
}