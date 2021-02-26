<template>
  <div class="has-text-centered">
    <h1 class="title text-center">Login</h1>
    <div class="row">
      <div class="col-md-6 offset-md-3">
        <form>
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" v-model="credentials.email" required>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="credentials.password" required>
          </div>
          <button type="button" class="btn btn-primary" @click="onSubmit">Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  name: 'Login',

  data() {
    return {
      credentials: {}
    }
  },

  methods: {
    ...mapActions({
      login: 'auth/login',
    }),

    onSubmit() {
      this.login(this.credentials).then(() => console.log('login page callback'));
    }
  }
}
</script>

<style scoped>

</style>