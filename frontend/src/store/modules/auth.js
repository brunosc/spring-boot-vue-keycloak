import UserService from '../../service/UserService';

export default {
  namespaced: true,

  state: {
    token: null,
  },

  mutations: {
    setToken(state, token) {
      state.token = token;
    }
  },

  actions: {
    login({ commit }, credentials) {
      return new Promise((resolve, reject) => {
        UserService.login(credentials)
          .then(result => {
            commit('setToken', result.data.access_token);
            console.log('LOGIN callback: ');
            console.log(result.data);
            resolve();
          })
          .catch(err => reject(err));
      });
    }
  },

  getters: {
    token: state => state.token,
  }
}
