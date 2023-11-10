import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  plusNum,
  quiz1Check,
  quiz2Check,
  quiz3Check,
  quiz4Check,
  quiz5Check,
  quiz6Check,
  quiz7Check,
} from "../../redux/slices/quizSlice";
import { useNavigate } from "react-router-dom";
import { getTrashDetailApi } from "../../apis/gameApi/gameApi";

const Modal = ({ num, trash_id, trash_name, trash_image, trash_location }) => {
  const [detail, setDetail] = useState({});
  const [isOpenQuiz, setIsOpenQuiz] = useState(true);
  const [isOpenDetail, setIsOpenDetail] = useState(false);

  const dispatch = useDispatch();
  const [selected, setSelected] = useState("");
  const onClick = (e) => {
    setSelected(e.target.value);
  };

  const getQuizDetail = async (trash_id) => {
    try {
      await getTrashDetailApi(trash_id).then((res) => {
        setDetail(res.data);
      });
    } catch (err) {
      console.log(err);
    }
  };

  const submitQuiz = () => {
    if (selected !== "") {
      getQuizDetail(trash_id);
      if (trash_location === selected) {
        dispatch(plusNum());
      }
      setIsOpenQuiz(false);
      setIsOpenDetail(true);
    } else {
      alert("퀴즈의 답을 선택해주세요!");
    }
  };

  const closeQuizDetail = () => {
    setIsOpenDetail(false);
    if (num === 1) {
      dispatch(quiz1Check());
      window.location.replace("/game");
    } else if (num === 2) {
      dispatch(quiz2Check());
      window.location.replace("/game");
    } else if (num === 3) {
      dispatch(quiz3Check());
      window.location.replace("/game");
    } else if (num === 4) {
      dispatch(quiz4Check());
      window.location.replace("/game");
    } else if (num === 5) {
      dispatch(quiz5Check());
      window.location.replace("/game");
    } else if (num === 6) {
      dispatch(quiz6Check());
      window.location.replace("/game");
    } else if (num === 7) {
      dispatch(quiz7Check());
    }
  };

  return (
    <div className="ModalFirstClass">
      {isOpenQuiz ? (
        <div className="ModalBorder">
          <div className="question">
            {num}. {trash_name}는 어디로 분리수거를 해야될까요?
          </div>
          <img src={trash_image} alt={trash_name} className="quizImage" />
          <div className="radio">
            <label>
              <input
                type="radio"
                name="location"
                value="플라스틱"
                onClick={onClick}
              />
              플라스틱
            </label>
            <label>
              <input
                type="radio"
                name="location"
                value="고철"
                onClick={onClick}
              />
              고철
            </label>
            <label>
              <input
                type="radio"
                name="location"
                value="일반쓰레기"
                onClick={onClick}
              />
              일반쓰레기
            </label>
            <label>
              <input
                type="radio"
                name="location"
                value="종이"
                onClick={onClick}
              />
              종이
            </label>
            <button onClick={submitQuiz}>선택</button>
          </div>
        </div>
      ) : isOpenDetail ? (
        <div className="ModalBorder">
          <div className="ModalTop">{trash_name}</div>
          <img src={trash_image} alt={trash_name} className="quizImg" />
          <div className="ModalContent">{detail.trash_detail}</div>
          <button onClick={closeQuizDetail}>Next</button>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
};

export default Modal;
