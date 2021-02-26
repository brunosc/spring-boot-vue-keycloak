import axios from 'axios';

export default class UserService {

  static login(credentials) {
    return axios.post('http://localhost:8080/api/users/token', credentials);
  }

  static user() {
    return axios.get('http://localhost:8080/api/users/protected-data');
  }

}