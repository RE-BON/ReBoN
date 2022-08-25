import '../../styles/post.css';
import '../../styles/post-star.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { useMediaQuery } from 'react-responsive';
import Header from '../../components/Header';
import PostModal from './PostModal';
// import Logout from '../Logout';
import { useState } from 'react';
import axios from 'axios';
import AWS from 'aws-sdk';

export default function Post() {
  //별 state
  const [pharase, setPharase] = useState(null);
  let state = pharase;
  const [starRate, setStarRate] = useState(null);

  //나만의 꿀팁 state
  const [myTip, setMyTip] = useState('');
  const onChangeMyTip = (e) => {
    setMyTip(e.target.value);
  };

  //콘텐츠 state
  const [myContent, setMyContent] = useState('');
  const onChangeMyContent = (e) => {
    setMyContent(e.target.value);
  };

  //이미지 state
  const [imageSrc, setImageSrc] = useState('');
  const region = 'us-east-1';
  const bucket = 'elice-boardgame-project';

  AWS.config.update({
    region: region,
    accessKeyId: 'AKIAWHWKYOWONYWK7TQK',
    secretAccessKey: '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',
  });

  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });

  const preview = (fileBlob) => {
    const reader = new FileReader();
    reader.readAsDataURL(fileBlob);

    return new Promise((resolve) => {
      reader.onload = () => {
        setImageSrc(reader.result);
        resolve();
      };
    });
  };

  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  const postSubmit = () => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    axios
      .post(
        `http://3.34.139.61:8080/api/shops/1/reviews`,
        //1에 ${shopId}
        {
          content: myContent,
          tip: myTip,
          imageUrls: [],
          star: starRate,
        },
        config
      )
      .then(function (response) {
        console.log(response.data);
      })
      .catch(function (error) {
        if (error.response.status === 400) {
          alert(error.response.data.message);
        } else console.log(error);
      });
  };

  return (
    <>
      {isMobile ? <div></div> : <Header />}

      <div className="post-wrapper">
        <div className="post-star">
          <form name="myform" id="myform" method="post" action="./save">
            <div className="star-wrapper">
              <fieldset>
                <input
                  onClick={() => {
                    setPharase('최고예요.');
                    setStarRate(5);
                  }}
                  type="radio"
                  name="rating"
                  value="5"
                  id="rate1"
                />
                <label htmlFor="rate1">★</label>
                <input
                  onClick={() => {
                    setPharase('좋아요.');
                    setStarRate(4);
                  }}
                  type="radio"
                  name="rating"
                  value="4"
                  id="rate2"
                />
                <label htmlFor="rate2">★</label>
                <input
                  onClick={() => {
                    setPharase('괜찮아요.');
                    setStarRate(3);
                  }}
                  type="radio"
                  name="rating"
                  value="3"
                  id="rate3"
                />
                <label htmlFor="rate3">★</label>
                <input
                  onClick={() => {
                    setPharase('그저 그래요.');
                    setStarRate(2);
                  }}
                  type="radio"
                  name="rating"
                  value="2"
                  id="rate4"
                />
                <label htmlFor="rate4">★</label>
                <input
                  onClick={() => {
                    setPharase('별로예요.');
                    setStarRate(1);
                  }}
                  type="radio"
                  name="rating"
                  value="1"
                  id="rate5"
                />
                <label htmlFor="rate5">★</label>
              </fieldset>
              <div className="star-wrapper-pharases">{state == null ? null : <span>{pharase}</span>}</div>
            </div>
          </form>
        </div>

        {isMobile ? (
          <div className="post-tip-mobile">
            <div className="post-tip-box-mobile">나만의 꿀팁</div>
            <input onChange={onChangeMyTip} value={myTip} placeholder="''마라도 횟집''에 대한 나만의 꿀팁을 적어주세요(ex.2층 창가자리 뷰가 예뻐요)" maxLength="500" />
          </div>
        ) : (
          <div className="post-tip">
            <div className="post-tip-box">나만의 꿀팁</div>
            <input onChange={onChangeMyTip} value={myTip} placeholder="''마라도 횟집''에 대한 나만의 꿀팁을 적어주세요(ex.2층 창가자리 뷰가 예뻐요)" maxLength="500" />
          </div>
        )}

        <div className="post-tip-count">{myTip.length}/500</div>

        <div className="post-review">
          <textarea rows="6" onChange={onChangeMyContent} value={myContent} placeholder="  ''마라도 횟집''에 대한 리뷰를 적어주세요" maxLength="500"></textarea>
          <div className="post-review-count">{myContent.length}/1000</div>
        </div>

        <div className="post-attach">
          <input
            type="file"
            id="input-file"
            accept=".gif, .jpg, .png"
            hidden
            onChange={(e) => {
              preview(e.target.files[0]);
            }}
          />
          <div className="post-attach-contents">
            {imageSrc === '' ? (
              <label className="post-attach-image" for="input-file">
                <FontAwesomeIcon icon={faPlus} size="1x" />
              </label>
            ) : (
              <img className="previewImg" src={imageSrc} />
            )}

            <div className="post-attach-count">1/10</div>
          </div>
        </div>
        <div className="post-button">
          <div className="post-button-cancel">취소</div>

          <div className="post-button-finish" onClick={postSubmit}>
            {!myTip || !myContent || !starRate ? '작성완료' : <PostModal />}
          </div>
        </div>
      </div>
    </>
  );
}
