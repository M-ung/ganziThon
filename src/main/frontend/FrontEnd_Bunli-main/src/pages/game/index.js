import { useEffect, useState } from "react";
import Modal from "./Modal.js";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { quizQuit } from "../../redux/slices/quizSlice.js";
import {
  getTrashListApi,
  postGameResultApi,
} from "../../apis/gameApi/gameApi.js";
import NewSideBar from "../components/menuBar/newSideBar.js";
import { updateMileage } from "../../redux/slices/userSlice.js";

const TrashList = {
  Trash: [
    {
      trash_id: 1,
      trash_name: "비닐코팅된 종이",
      trash_image:
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgaHvaL-nlI1Klm5HP6dLFb9fd7YgwySkFwg&usqp=CAU",
      trash_location: "일반쓰레기",
    },
    {
      trash_id: 2,
      trash_name: "고무장갑",
      trash_image:
        "https://m.officeplus.com/images/prdimg/I/IS/IS2/IS2166_ma.jpg",
      trash_location: "일반쓰레기",
    },
    {
      trash_id: 3,
      trash_name: "페트병",
      trash_image: "https://bottleworld.co.kr/data/bottle/KJ1902.jpg",
      trash_location: "플라스틱",
    },
    {
      trash_id: 4,
      trash_name: "캔",
      trash_image:
        "https://cdn-acemall.bizhost.kr/files/goods/393/2400019200008_b2.jpg",
      trash_location: "고철",
    },
    {
      trash_id: 5,
      trash_name: "글씨가 써진 이면지",
      trash_image:
        "https://ojsfile.ohmynews.com/STD_IMG_FILE/2008/0728/IE000943606_STD.jpg",
      trash_location: "종이",
    },
    {
      trash_id: 6,
      trash_name: "양은냄비",
      trash_image:
        "https://blisgo.com/wp-content/uploads/elementor/thumbs/%EB%83%84%EB%B9%84-ouwom5yko0p6b3wxzu1tbpqn465av122lkxksivgn4.jpg",
      trash_location: "고철",
    },
    {
      trash_id: 7,
      trash_name: "음식물 플라스틱 용기",
      trash_image:
        "https://img.segye.com/content/image/2019/12/22/20191222504135.jpg",
      trash_location: "플라스틱",
    },
  ],
  user_mileage: 31231,
};

const Game = () => {
  const token = localStorage.getItem("token");

  const [trashList, setTrashList] = useState({});
  const getTrashList = async () => {
    try {
      await getTrashListApi().then((res) => {
        setTrashList(res.data);
      });
    } catch (err) {
      console.log(err);
    }
  };

  const mileage = useSelector(
    (state) => state.persistedReducer.user.user_mileage
  );

  const num = useSelector((state) => state.persistedReducer.quiz.num);
  const plusMileage = num * 100;

  const isNumber1 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber1
  );
  const isNumber2 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber2
  );
  const isNumber3 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber3
  );
  const isNumber4 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber4
  );
  const isNumber5 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber5
  );
  const isNumber6 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber6
  );
  const isNumber7 = useSelector(
    (state) => state.persistedReducer.quiz.isNumber7
  );

  useEffect(() => {
    getTrashList();
  }, []);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const postResult = async (totalMileage, todayTime, token) => {
    try {
      await postGameResultApi(totalMileage, todayTime, token).then((res) => {
        console.log(res);
      });
    } catch (err) {
      console.log(err);
    }
  };

  const backHome = () => {
    const today = new Date();
    const totalMileage = mileage + plusMileage;
    const todayTime = `${today.getFullYear()}-${
      today.getMonth() + 1
    }-${today.getDate()} ${today.getHours().toString().padStart(2, "0")}:${today
      .getMinutes()
      .toString()
      .padStart(2, "0")}`;

    postResult(totalMileage, todayTime, token);
    dispatch(
      updateMileage({
        user_mileage: totalMileage,
      })
    );
    alert(
      `\n총 7문제중 ${num}문제를 맞추었습니다!!\n마일리지 ${plusMileage}점 획득!!`
    );

    navigate("/");
    dispatch(quizQuit());
  };

  return (
    <div>
      <div className="gameMainFirstClass">
        <NewSideBar />
        {!isNumber1 ? (
          <Modal
            num={1}
            trash_id={trashList.Trash[0].trash_id}
            trash_name={trashList.Trash[0].trash_name}
            trash_image={trashList.Trash[0].trash_image}
            trash_location={trashList.Trash[0].trash_location}
          />
        ) : !isNumber2 ? (
          <Modal
            num={2}
            trash_id={trashList.Trash[1].trash_id}
            trash_name={trashList.Trash[1].trash_name}
            trash_image={trashList.Trash[1].trash_image}
            trash_location={trashList.Trash[1].trash_location}
          />
        ) : !isNumber3 ? (
          <Modal
            num={3}
            trash_id={trashList.Trash[2].trash_id}
            trash_name={trashList.Trash[2].trash_name}
            trash_image={trashList.Trash[2].trash_image}
            trash_location={trashList.Trash[2].trash_location}
          />
        ) : !isNumber4 ? (
          <Modal
            num={4}
            trash_id={trashList.Trash[3].trash_id}
            trash_name={trashList.Trash[3].trash_name}
            trash_image={trashList.Trash[3].trash_image}
            trash_location={trashList.Trash[3].trash_location}
          />
        ) : !isNumber5 ? (
          <Modal
            num={5}
            trash_id={trashList.Trash[4].trash_id}
            trash_name={trashList.Trash[4].trash_name}
            trash_image={trashList.Trash[4].trash_image}
            trash_location={trashList.Trash[4].trash_location}
          />
        ) : !isNumber6 ? (
          <Modal
            num={6}
            trash_id={trashList.Trash[5].trash_id}
            trash_name={trashList.Trash[5].trash_name}
            trash_image={trashList.Trash[5].trash_image}
            trash_location={trashList.Trash[5].trash_location}
          />
        ) : !isNumber7 ? (
          <Modal
            num={7}
            trash_id={trashList.Trash[6].trash_id}
            trash_name={trashList.Trash[6].trash_name}
            trash_image={trashList.Trash[6].trash_image}
            trash_location={trashList.Trash[6].trash_location}
          />
        ) : (
          backHome()
        )}
      </div>
    </div>
  );
};

export default Game;
