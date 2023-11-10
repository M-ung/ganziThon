import axios from "axios";

const url = "http://localhost:8080";

// 게임화면 접속, 쓰레기들 정보(id,name,img,location) 받기, 유저마일리지 받을 필요 없음
export const getTrashListApi = () => {
  return axios.get(`${url}/page/game`);
};

// trash_id에 해당하는 쓰레기의 설명을 받는다. 추가 쓰레기 리스트 필요없음
export const getTrashDetailApi = (trash_id) => {
  return axios.get(`${url}/page/game/${trash_id}`);
};

// 게임 종료 후 총마일리지와 현재시간 보내기
export const postGameResultApi = (totalMileage, todayTime, token) => {
  return axios.post(
    `${url}/page/game/finish`,
    {
      user_mileage: totalMileage,
      game_time: todayTime,
    },
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
};
