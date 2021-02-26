import router from '../../router'
import UserService from '../../service/UserService';

export default {
  namespaced: true,

  state: {
    token: null,
    user: null,
  },

  mutations: {
    setToken(state, token) {
      state.token = token;
    },
    setUser(state, user) {
      state.user = user;
    }
  },

  actions: {
    login({ commit }, credentials) {
      return new Promise((resolve, reject) => {
        UserService.login(credentials)
          .then(result => {
            commit('setToken', result.data.access_token);
            localStorage.setItem('token', result.data.access_token);

            router.replace('/');
            resolve();
          })
          .catch(err => reject(err));
      });
    },

    fetchUser({ commit }) {
      UserService.user()
        .then(result => {
          commit('setUser', result.data);
          localStorage.setItem('user', JSON.stringify(result.data));
        })
    }
  },

  getters: {
    token: state => state.token,
    user: state => state.user,
    isAuthenticated: state => state.token !== null,
  }
}
