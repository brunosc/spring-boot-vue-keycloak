<template>
  <div>
    <h1>Home</h1>
    <p>Hello {{ username }}</p>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'Home',

  created() {
    this.fetchUser();
  },

  methods: {
    ...mapActions({
      fetchUser: 'auth/fetchUser',
    }),
  },

  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),

    username() {
      return this.user ? this.user.firstName : '';
    }
  }

}
</script>

<style scoped>

</style>