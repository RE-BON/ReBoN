import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import Tags from './Tags';
import Header from '../../components/Header';
import '../../styles/search.css';
export default function Search() {
  return (
    <div className="search-wrapper">
      <Header />
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
