import { useNavigate } from "react-router-dom";
import recyclebinImg from "../../../assets/recylebinImg.png";
import storeImg from "../../../assets/제어판.png";
import userImg from "../../../assets/드라이브.png";

const NewSideBar = () => {
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
    <div id="iconBox">
      <img src={userImg} id="mypage" onClick={handleOnMypage}></img>
      <p id="font1">마이페이지</p>
      <img src={recyclebinImg} id="game" onClick={handleOnGame}></img>
      <p id="font2">휴지통</p>
      <img src={storeImg} id="store" onClick={handleOnStore}></img>
      <p id="font3">상점</p>
    </div>
  );
};

export default NewSideBar;
