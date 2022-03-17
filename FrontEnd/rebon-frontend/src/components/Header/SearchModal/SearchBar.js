import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import '../../../styles/header-search-bar.css';

export default function Searchbar() {
  return (
    <div className="search-bar-wrapper">
      <input placeholder="가고 싶은 지역을 입력해주세요" />
      <FontAwesomeIcon icon={faSearch} className="search" />
    </div>
  );
}
