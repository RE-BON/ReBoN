import React, { useState } from 'react';
import styled from 'styled-components';
import Modal, { ModalProvider, BaseModalBackground } from 'styled-react-modal';
import './../../../../styles/review-modal.css';
import { Link } from 'react-router-dom';
import { Dropdown } from 'react-bootstrap';
import { MoreVertical, Trash, Edit, X } from 'react-feather';
export default function FootprintModal({ info, handleDelete }) {
  const [isOpen, setIsOpen] = useState(false);

  function toggleModal(e) {
    setIsOpen(!isOpen);
  }

  function onClickDelete(targetId) {
    handleDelete(targetId);
  }

  function onClickModify(target) {
    console.log(target);
  }

  const CustomToggle = React.forwardRef(({ children, onClick }, ref) => (
    <Link
      to=""
      ref={ref}
      onClick={(e) => {
        e.preventDefault();
        onClick(e);
      }}
    >
      {children}
    </Link>
  ));

  const FadingBackground = styled(BaseModalBackground)`
    opacity: ${(props) => props.opacity};
    transition: all 0.3s ease-in-out;
  `;
  return (
    <ModalProvider backgroundComponent={FadingBackground}>
      <Dropdown>
        <Dropdown.Toggle as={CustomToggle}>
          <MoreVertical size="15px" className="text-secondary" />
        </Dropdown.Toggle>
        <Dropdown.Menu align="end">
          <Dropdown.Item eventKey="1">
            {/* url 위치 알맞은 데로 가기 */}
            <Link
              to={`/modify/${info.id}`}
              state={{ star: info.star, content: info.content, id: info.id, images: info.images, name: info.shopName, tip: info.tip }}
              style={{ color: 'inherit', textDecoration: 'none' }}
            >
              <div
                onClick={() => {
                  onClickModify(info);
                  toggleModal();
                }}
              >
                <Edit size="18px" className="dropdown-item-icon" /> <p className="review-modal-report">리뷰수정</p>
              </div>
            </Link>
          </Dropdown.Item>
          <Dropdown.Item eventKey="2">
            <div
              onClick={() => {
                onClickDelete(info.id);
                toggleModal();
              }}
            >
              <Trash size="18px" className="dropdown-item-icon" /> <p className="review-modal-report">리뷰삭제</p>
            </div>
          </Dropdown.Item>
          <Dropdown.Item eventKey="3">
            <X size="18px" className="dropdown-item-icon" /> <p className="review-modal-close">닫기</p>
          </Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
    </ModalProvider>
  );
}

const StyledModal = Modal.styled`
  width: 22rem;
  height: 23rem;
  padding : 20px;
  border-radius:20px;
  background-color: white;
  opacity: ${(props) => props.opacity};
  transition : all 0.3s ease-in-out;`;
