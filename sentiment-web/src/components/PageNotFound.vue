<template id="button-toggle-group">
  <div class="wrapper">
    <h1 class="display-2">Page Not Found</h1>
    <h3 class="display-5">The page you requested was not found</h3>

    <div class="section">
      <form @submit.prevent="submit">
        <button type="submit" class="btn btn-primary">Return to Homepage</button>
      </form>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'PageNotFound',
    methods: {
      submit: function () {
        this.$router.push({ name: 'CreateAnalysisRequest'})
      }
    }
  }
</script>
