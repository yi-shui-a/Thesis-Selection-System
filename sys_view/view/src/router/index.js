import Vue from 'vue'
import VueRouter from 'vue-router'
import login from "@/views/login";



import teacherhome from "@/views/teacher/TeacherHome/teacherhome";
import teacher from "@/views/teacher";
import BaseLayOut from "@/components/BaseLayOut";
import paperFindQurey from "@/views/teacher/paperFindQurey";
import NeedCheckPaper from "@/views/teacher/Paper/NeedCheckPaper";
import editorTeacherInfo from "@/views/teacher/TeacherHome/editorTeacherInfo";
import teacheRresetPassword from "@/views/teacher/TeacherHome/teacheRresetPassword";
import Mypaper from "@/views/teacher/Paper/Mypaper";
import TeacherEditPaper from "@/views/teacher/Paper/TeacherEditPaper";
import TeacherPaperHistory from "@/views/teacher/Paper/TeacherPaperHistory";
import AbleTeachNum from "@/views/teacher/TeacherHome/AbleTeachNum";
import CreatePaper from "@/views/teacher/Paper/CreatePaper";
import StudentIndex from "@/views/student/StudentIndex";
import StudentHome from "@/views/student/StudentHome/StudentHome";
import StudentEditPassword from "@/views/student/StudentHome/StudentEditPassword";
import StudentEditInfo from "@/views/student/StudentHome/StudentEditInfo";
import StudentMyPaper from "@/views/student/Paper/StudentMyPaper";
import StudentAblePaper from "@/views/student/Paper/StudentAblePaper";
import StudentHistoryPaper from "@/views/student/Paper/StudentHistoryPaper";
import AdminIndex from "@/views/admin/adminIndex";
import AdminHome from "@/views/admin/AdminHome/AdminHome";
import EditAdminInfo from "@/views/admin/AdminHome/EditAdminInfo";
import AdminEditPassword from "@/views/admin/AdminHome/AdminEditPassword";
import AdminPaper from "@/views/admin/Paper/AdminPaper";
import AdminCheckPaper from "@/views/admin/Paper/AdminCheckPaper";
import AdminEditPaper from "@/views/admin/Paper/AdminEditPaper";
import AdminTitleType from "@/views/admin/AdminDict/AdminTitleType";
import AdminTeacherEducation from "@/views/admin/AdminDict/AdminTeacherEducation";
import AdminTeacherTitle from "@/views/admin/AdminDict/AdminTeacherTitle";
import AdminSchoolYear from "@/views/admin/AdminDict/AdminSchoolYear";
import AdminAddStudent from "@/views/admin/AdminStudent/AdminAddStudent";
import AdminStudentList from "@/views/admin/AdminStudent/AdminStudentList";
import AdminEditStudent from "@/views/admin/AdminStudent/AdminEditStudent";
import AdminAddTeacher from "@/views/admin/AdminTeacher/AdminAddTeacher";
import AdminTeacherList from "@/views/admin/AdminTeacher/AdminTeacherList";
import AdminEditTeacher from "@/views/admin/AdminTeacher/AdminEditTeacher";
import AdminTeacherTeachNum from "@/views/admin/AdminTeacher/AdminTeacherTeachNum";
import AdminSelectDeadLine from "@/views/admin/AdminDict/AdminSelectDeadLine";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/teacher/edit_paper',
    name: '编辑选题',
    component: TeacherEditPaper,
    meta: {requireAuth: true}
  },
  {
    path: '/admin/edit_paper',
    name: '编辑选题',
    component: AdminEditPaper,
    meta: {requireAuth: true}
  },
  {
    path: '/admin/edit_student',
    name: '编辑学生',
    component: AdminEditStudent,
    meta: {requireAuth: true}
  },
  {
    path: '/admin/edit_teacher',
    name: '编辑教师',
    component: AdminEditTeacher,
    meta: {requireAuth: true}
  },
  {
    path: '/admin/edit_teacher_can_teach_num',
    name: '编辑教师可教人数',
    component: AdminTeacherTeachNum,
  },
  {
    path:'/teacher',
    name:'teacher',
    meta:{requireAuth:true},
    redirect: 'home',
    component:BaseLayOut,
    children:[
      {path:'/teacher',
        name:'我的主页',
        component: teacher,
        meta: {requireAuth: true},
        children: [
          {
            path: '/teacher/home',
            name: '个人信息',
            component: teacherhome,
            meta: {requireAuth: true}
          },
          {
            path: '/teacher/teach_num',
            name: '教学人数',
            component: AbleTeachNum,
            meta: {requireAuth: true}
          },
          {
            path: '/teacher/edit_info',
            name: '修改信息',
            component: editorTeacherInfo,
            meta: {requireAuth: true}
          },
            {
                path: '/teacher/edit_password',
                name: '修改密码',
                component: teacheRresetPassword,
                meta: {requireAuth: true}
            }

        ]
      },
      {
        path:'/paper',
        name:'选题管理',
        component: teacher,
        meta: {requireAuth: true},
        children:[
          {
            path: '/teacher/create_paper',
            name: '创建选题',
            component: CreatePaper,
            meta: {requireAuth: true}
          },
          {
            path: '/teacher/my_paper',
            name: '我的选题',
            component: Mypaper,
            meta: {requireAuth: true,}
          },

         {
          path:'/teacher/check_paper',
          name:'审核选题',
          component: NeedCheckPaper,
          meta: {requireAuth: true},
        },
          {
            path:'/teacher/paper_history',
            name:'学生选题历史',
            component: TeacherPaperHistory,
            meta: {requireAuth: true},
          },

          // {
          //   path:'/teacher/test',
          //   name:'查询条件测试',
          //   component: paperFindQurey,
          //   meta: {requireAuth: true}
          // }
        ]
      }
    ],
  },
  {
    path: '/student',
    name: 'student',
    meta: {requireAuth: true},
    redirect: '/student/home',
    component: BaseLayOut,
    children: [
      {
        path: '/student',
        name: '我的主页',
        component: StudentIndex,
        meta: {requireAuth: true},
        children: [
          {
            path: '/student/home',
            name: '个人信息',
            component: StudentHome,
            meta: {requireAuth: true}
          },
            {
              path: '/student/edit_password',
              name: '修改密码',
              component: StudentEditPassword,
                meta: {requireAuth: true}
            },
          {
            path: '/student/edit_info',
            name: '修改信息',
            component: StudentEditInfo,
            meta: {requireAuth: true}
          }
        ]
      },
      {
        path: '/student/paper',
        name: '选题管理',
        component: StudentIndex,
        meta: {requireAuth: true},
        children: [
          {
            path: '/student/choose_paper',
            name: '选题',
            component: StudentAblePaper,
            meta: {requireAuth: true}
          },
          {
            path: '/student/my_paper',
            name: '我的选题',
            component: StudentMyPaper,
            meta: {requireAuth: true}
          },
          {
            path: '/student/paper_history',
            name: '选题历史',
            component: StudentHistoryPaper,
            meta: {requireAuth: true}
          }

        ]
      }
    ]
  },
  {
    name:'admin',
    path:'/admin',
    meta:{requireAuth:true},
    redirect:'/admin/home',
    component:BaseLayOut,
    children:[
      {
        path:'/admin',
        name:'我的主页',
        component:AdminIndex,
        meta:{requireAuth:true},
        children:[
          {
            path:'/admin/home',
            name:'个人信息',
            component: AdminHome,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/edit_info',
            name:'修改信息',
            component: EditAdminInfo,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/edit_password',
            name:'修改密码',
            component: AdminEditPassword,
            meta:{requireAuth:true}
          }
        ]
      },
      {
        path:'/admin/paper',
        name:'选题管理',
        component:AdminIndex,
        meta:{requireAuth:true},
        children:[
          {
            path:'/admin/paper_list',
            name:'选题列表',
            component: AdminPaper,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/check_paper',
            name:'审核选题',
            component: AdminCheckPaper,
            meta:{requireAuth:true}
          }
        ]
      },
      {
        path:'/admin/dict',
        name:'字典管理',
        component:AdminIndex,
        meta:{requireAuth:true},
        children:[
          {
            path:'/admin/title_type',
            name:'选题类型',
            component: AdminTitleType,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/teacher_education',
            name:'教师学历',
            component: AdminTeacherEducation,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/teacher_title',
            name:'教师职称',
            component: AdminTeacherTitle,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/schoolyear',
            name:'学年类型',
            component: AdminSchoolYear,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/deadline',
            name:'截止时间',
            component: AdminSelectDeadLine,
            meta:{requireAuth:true}
          }
        ]
      },
      {
        path:'/admin/student',
        name:'学生管理',
        component:AdminIndex,
        meta:{requireAuth:true},
        children:[
          {
            path:'/admin/add_student',
            name:'添加学生',
            component: AdminAddStudent,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/student_list',
            name:'学生列表',
            component: AdminStudentList,
            meta:{requireAuth:true}
          }
        ]
      },
      {
        path:'/admin/teacher',
        name:'教师管理',
        component:AdminIndex,
        meta:{requireAuth:true},
        children:[
          {
            path:'/admin/add_teacher',
            name:'添加教师',
            component: AdminAddTeacher,
            meta:{requireAuth:true}
          },
          {
            path:'/admin/teacher_list',
            name:'教师列表',
            component: AdminTeacherList,
            meta:{requireAuth:true}
          }

        ]
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

/*
  session 设置：
    1. token
 */
router.beforeEach((to, from, next) => {
  console.log(from.path + ' ====> ' + to.path)
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (sessionStorage.getItem("token") !=null) { // 判断本地是否存在token
      next()
    } else {
      // 未登录,跳转到登陆页面
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  } else {
    // 不需要登陆权限的页面，如果已经登陆，则跳转主页面
    if(sessionStorage.getItem("token") === 'true'){
      const t = sessionStorage.getItem("type")
      next('/' + t);
    }else{
      next();
    }
  }
});
