import axios from 'axios'
export function GetAdminInfo(uid){
    return axios({
        url: '/admin/get_myinfo',
        method: 'GET',
        params:{
            uid: uid
        }
    })
}
export function UpdateAdminInfo(data){
    return axios({
        url: '/admin/update_myinfo',
        method: 'POST',
        data: data
    })
}
export function UpdateAdminPassword(uid,password){
    return axios({
        url: '/admin/update_password',
        method: 'POST',
        params:{
            uid: uid,
            password: password
        }
    })
}
export function GetAllPaperAdmin(query){
    return axios({
        url: '/admin/get_all_paper',
        method: 'GET',
        params:{
            teacherName: query.teacherName,
            type: query.type,
            checkStatus: query.checkStatus,
            title: query.title,
            schoolyear: query.schoolyear,
        }
    })
}
export function GetAllPaperNeedCheckAdmin(query){
    return axios({
        url: '/admin/get_all_paper',
        method: 'GET',
        params:{
            teacherName: query.teacherName,
            type: query.type,
            checkStatus: 'NOT_CHECK',
            title: query.title,
            schoolyear: query.schoolyear,
        }
    })
}

export function AdminAcceptPaper(data){
    return axios({
        url: '/admin/check_paper',
        method: 'POST',
        data: data
    })
}
export function AdminRefusePaper(data){
    return axios({
        url: '/admin/refuse_paper',
        method: 'POST',
        data: data
    })
}
export function UpdateCanTeachNum(data){
    return axios({
        url: '/utils/update_can_teach_num',
        method: 'POST',
        data: data
    })
}
export function UpdateCanSelectNum(data){
    return axios({
        url: '/utils/update_can_select_num',
        method: 'POST',
        data: data
    })
}
export function ResetStudentPassword(sno){
    return axios({
        url: '/utils/reset_student',
        method: 'POST',
        params:{
            sno: sno
        }
    })
}
export function ResetTeacherPassword(tno){
    return axios({
        url: '/utils/reset_teacher',
        method: 'POST',
        params:{
            tno: tno
        }
    })
}
export function UpdateSelectDeadLine(selectDeadline){
    return axios({
        url: '/utils/update_select_deadline',
        method: 'POST',
        params:{
            selectDeadline: selectDeadline
        }
    })
}
//以下为字典类型的操作
export function AddTitleType(type){
    return axios({
        url: '/utils/add_title_type',
        method: 'POST',
        params:{
            type: type
        }
    })
}
export function DeleteTitleType(type){
    return axios({
        url: '/utils/delete_title_type',
        method: 'POST',
        params:{
            type: type
        }
    })
}
export function AddEducation(edu){
    return axios({
        url: '/utils/add_education',
        method: 'POST',
        params:{
            type: edu
        }
    })
}
export function DeleteEducation(edu){
    return axios({
        url: '/utils/delete_education',
        method: 'POST',
        params:{
            type: edu
        }
    })
}
export function AddTeacherTitle(title){
    return axios({
        url: '/utils/add_title',
        method: 'POST',
        params:{
            type: title
        }
    })
}
export function DeleteTeacherTitle(title){
    return axios({
        url: '/utils/delete_title',
        method: 'POST',
        params:{
            type: title
        }
    })
}
export function AddSchoolyear(schoolyear){
    return axios({
        url: '/utils/add_schoolyear',
        method: 'POST',
        params:{
            schoolyear: schoolyear
        }
    })
}
export function DeleteSchoolyear(schoolyear){
    return axios({
        url: '/utils/delete_schoolyear',
        method: 'POST',
        params:{
            schoolyear: schoolyear
        }
    })
}
export function AddStudent(student){
    return axios({
        url: '/utils/add_student',
        method: 'POST',
        data: student
    })
}
export function GetStudentList(){
    return axios({
        url: '/utils/get_student_list',
        method: 'GET',
    })
}
// export function UpdateCanSelectNum(sno,ableTopicNum){
//     return axios({
//         url: '/utils/update_can_select_num',
//         method: 'POST',
//         params:{
//             sno: sno,
//             ableTopicNum: ableTopicNum
//         }
//     })
// }
export function AddTeacher(teacher){
    return axios({
        url: '/utils/add_teacher',
        method: 'POST',
        data: teacher
    })
}
export function GetTeacherList(){
    return axios({
        url: '/utils/get_teacher_list',
        method: 'GET',
    })
}
export function UpdateCanTeachNumPiLiang(data){
    return axios({
        url: '/utils/update_can_teach_num_piliang',
        method: 'POST',
        data: data
    })
}
