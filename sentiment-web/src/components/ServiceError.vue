<template>
  <div class="wrapper">
    <h1 class="display-2">Service Error</h1>
    <h3 class="display-5">We are currently experiencing issues. Please try again at a later time.</h3>

    <div class="section">
      <form @submit.prevent="submit">
        <button type="submit" class="btn btn-primary">Return to Homepage</button>
      </form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ServiceError',
    methods: {
      submit: function () {
        this.$router.push({ name: 'CreateAnalysisRequest'})
      }
    }
  }
</script>
