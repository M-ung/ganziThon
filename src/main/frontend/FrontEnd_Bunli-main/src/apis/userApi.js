import axios from "axios";

const url = "http://localhost:8080";

// 유저정보 받기
export const userInfoApi = (token) => {
  return axios.get(`${url}/myPage`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

// 로그아웃
export const userLogoutApi = (token) => {
  return axios.post(`${url}/user/out`, {}, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};