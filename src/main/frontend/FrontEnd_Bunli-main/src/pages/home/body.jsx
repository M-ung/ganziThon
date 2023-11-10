import { HomeBody } from "./style";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { userInfoApi } from "../../apis/userApi.js";
import { useDispatch } from "react-redux";
import { login } from "../../redux/slices/userSlice.js";

function Body() {
  const token = localStorage.getItem("token");
  const dispatch = useDispatch();
  const [userInfo, setUserInfo] = useState({});

  const getUserInfo = async (token) => {
    try {
      await userInfoApi(token).then((res) => {
        setUserInfo(res.data);
        dispatch(
            login({
              user_id: userInfo.user_id,
              token: token,
              user_name: userInfo.user_name,
              user_mileage: userInfo.user_mileage,
              user_address: userInfo.user_address,
            })
        );
      });
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getUserInfo(token);
  }, []);

  const navigate = useNavigate();

  // 유저 아이콘 클릭 시
  const handleOnMypage = (e) => {
    // 마이페이지로 라우팅
    navigate("/mypage");
  };

  // 휴지통 아이콘 클릭 시
  const handleOnGame = (e) => {
    // 게임으로 라우팅
    navigate("/game");
  };

  // 상점 아이콘 클릭 시
  const handleOnStore = (e) => {
    //상점으로 라우팅
    navigate("/giftshop");
  };

  return (
      <HomeBody>
        <div id="background">
          <div id="trashBox">
          </div>
          <div id="iconBox">
            <p id="mypage" onClick={handleOnMypage}></p>
            <p id="font1">마이페이지</p>
            <p id="game" onClick={handleOnGame}></p>
            <p id="font2">휴지통</p>
            <p id="store" onClick={handleOnStore}></p>
            <p id="font3">상점</p>
          </div>
        </div>
      </HomeBody>
  );
}

export default Body;
