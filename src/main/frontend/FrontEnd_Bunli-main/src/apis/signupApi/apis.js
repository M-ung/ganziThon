import axios from "axios";

// axios 인스턴스
// 수정 필요
const serverApi = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

// // signup
// export const createUser = async (navigate, userName, id, password, userAddress) => {
//     await serverApi.post(`http://localhost:8080/user/signup`, {'user_name':userName, 'user_email':id, 'user_password':password, 'user_address': userAddress}).then((response)=>{
//         authenticateUser(navigate, id, password);
//     })
// }

// 수정한 signup
export const createUser = async (navigate, name, id, password, address) => {
  await serverApi
    .post(`http://localhost:8080/user/signup`, {
      userName: name,
      userEmail: id,
      userPassword: password,
      userAddress: address,
    })
    .then((response) => {
      authenticateUser(navigate, id, password);
    });
};

// login시 유저인지 확인하는 함수
//response로 토큰 받아서 로컬스토리지 저장
export const authenticateUser = async (navigate, id, password) => {
  await serverApi
    .post(`http://localhost:8080/user/login`, {
      userEmail: id,
      userPassword: password,
    })
    .then((response) => {
      localStorage.setItem("token", response.data.token);
      navigate(`/home`);
    });
};
