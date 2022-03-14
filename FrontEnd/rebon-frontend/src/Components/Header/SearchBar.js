import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

function SearchBar() {
  return (
    <Wrapper>
      <Input placeholder="가고 싶은 지역을 입력해주세요" />
      <FontAwesomeIcon icon={faSearch} className="search" />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 389px;
  height: 38px;
  padding: 20px;
  border-radius: 100px;
  background-color: rgba(0, 0, 0, 0.05);
  color: rgba(0, 0, 0, 0.4);

  .search {
    color: rgba(0, 0, 0, 0.8);
  }
`;

const Input = styled.input`
  width: 90%;
  height: 35px;
  border-radius: 50px;
  background-color: transparent;
  border: none;
  &:focus {
    outline: none;
  }
`;

export default SearchBar;
