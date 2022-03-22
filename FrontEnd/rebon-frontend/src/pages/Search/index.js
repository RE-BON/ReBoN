import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import Tags from './Tags';
import '../../styles/search.css';
// 이 콘텐츠 이름은 다른 분들것과 따라 맞추자
export default function Search() {
  return (
    <div className="search-wrapper">
      <div className="search-content">
        <div className="title">
          가고 싶은 지역을 입력해서 <br />
          <span>맛집, 숙소</span>를 찾아보세요!
        </div>

        <div className="input-bar">
          <input />
          <FontAwesomeIcon icon={faSearch} className="search" />
        </div>

        <Tags />
      </div>
    </div>
  );
}
