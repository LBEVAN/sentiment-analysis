import Vue from 'vue'
import Router from 'vue-router'
import CreateAnalysisRequest from '@/components/CreateAnalysisRequest'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'CreateAnalysisRequest',
      component: CreateAnalysisRequest
    }
  ]
})
