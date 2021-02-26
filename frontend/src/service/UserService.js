import axios from 'axios';

export default class UserService {

  static login(credentials) {
    return axios.post('http://localhost:8080/api/users/token', credentials);
  }

}