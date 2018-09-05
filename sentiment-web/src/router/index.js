import Vue from 'vue'
import Router from 'vue-router'
import CreateAnalysisRequest from '@/components/CreateAnalysisRequest'
import ViewAnalysisRequest from '@/components/ViewAnalysisRequest'
import PageNotFound from '@/components/PageNotFound'
import ServiceError from '@/components/ServiceError'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'CreateAnalysisRequest',
      component: CreateAnalysisRequest
    },
    {
      path: '/request/:id',
      name: 'ViewAnalysisRequest',
      component: ViewAnalysisRequest
    },
    {
      path: '/service-error',
      component: ServiceError
    },
    {
      path: '/404',
      component: PageNotFound
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
