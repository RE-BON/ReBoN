import '../../styles/post.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import Header from '../../components/Header';
import Star from './Star';
import PostModal from './PostModal';

export default function Post() {
  return (
    <>
      <Header />
      <div className="post-wrapper">
        <div className="post-title">리뷰쓰기</div>

        <div className="post-star">
          <Star />
        </div>

        <div className="post-tip">
          <div className="post-tip-box">나만의 꿀팁</div>
          <input placeholder="''마라도 횟집''에 대한 나만의 꿀팁을 적어주세요(ex.2층 창가자리 뷰가 예뻐요)" maxLength="500" />
        </div>
        <div className="post-tip-count">1/500</div>

        <div className="post-review">
          <textarea rows="6" placeholder="  ''마라도 횟집''에 대한 리뷰를 적어주세요" maxLength="500"></textarea>
          <div className="post-review-count">1/1000</div>
        </div>

        <div className="post-attach">
          <div className="post-attach-contents">
            <div className="post-attach-image">
              <FontAwesomeIcon icon={faPlus} size="1x" />
            </div>
            <div className="post-attach-count">1/10</div>
          </div>
        </div>
        <div className="post-button">
          <div className="post-button-cancel">취소</div>
          <div className="post-button-finish">
            <PostModal />
          </div>
        </div>
      </div>
    </>
  );
}
