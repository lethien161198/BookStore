-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 06, 2020 lúc 04:57 AM
-- Phiên bản máy phục vụ: 10.4.13-MariaDB
-- Phiên bản PHP: 7.4.7
-- THien 123

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `book`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(1000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 34, 3, 'Sọ Dừa', 60000, 3),
(2, 35, 1, 'JavaCore', 490000, 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(200) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `email`) VALUES
(34, 'thien11', 354592277, 'lethien@gmail.com'),
(35, 'thien', 123, 'thien@');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhloaisanpham`) VALUES
(1, 'Sách', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR8lTdfx3B3k7J7DK37BU0uybaYcfVppVBKLQ&usqp=CAU'),
(2, 'Truyện', 'https://maclife.vn/wp-content/uploads/2019/11/Comic-Life-3-icon.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhsanpham` varchar(200) NOT NULL,
  `motesanpham` varchar(10000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhsanpham`, `motesanpham`, `idsanpham`) VALUES
(1, 'JavaCore', 70000, 'https://thaythanhjava.files.wordpress.com/2017/09/anhnen.png?w=379&h=534', 'Học lập trình Java', 1),
(2, 'PHP & MySQL', 85000, 'https://vnwriter.net/wp-content/uploads/2018/05/sach-lap-trinh-co-ban-php-va-mysql-210x300.png', 'Học PHP and MySQL', 1),
(3, 'Sọ Dừa', 20000, 'https://upload.wikimedia.org/wikipedia/vi/9/96/So_dua_bia_truyen.jpg', 'Truyện Cổ Tích', 2),
(4, 'Doremon', 20000, 'https://paintcorner.net/wp-content/uploads/2018/11/huong-dan-ve-bia-truyen-tranh-co-ban-hinh-anh2-e1542394983463.jpg', 'Doremon', 2),
(5, 'Oxford Advanced Learner\'s Dictionary 8th Edition ', 50000, 'https://img.websosanh.vn/v2/users/wss/images/oxford-advanced-learners/rrhvlewlvneaa.jpg?compress=85&width=200', 'Oxford Advanced Learner\'s Dictionary (viết tắt: OALD) bao gồm CD-ROM - từ điển bán chạy nhất thế giới - vừa ra mắt ấn bản Anh - Việt. Để cuốn sách này đến tay bạn đọc trong nước, đơn vị thực hiện mất hơn một năm làm việc với đối tác là Nhà xuất bản Oxford, từ việc xây dựng quy trình làm việc, thành lập nhóm biên dịch đến tìm kiếm đơn vị phần mềm tại nước ngoài để chuyển tải dữ liệu gốc. Sau đó, Giáo sư - tiến sĩ Nguyễn Văn Hiệp - Viện Trưởng Viện Ngôn ngữ học, Phó giáo sư - tiến sĩ Đinh Điền, học giả Fulbright Trần Mạnh Quang cùng đội ngũ hơn 30 chuyên gia biên dịch trong lĩnh vực từ điển bắt tay thực hiện ấn phẩm.', 1),
(6, 'Nhà Giả Kim', 70000, 'https://gacsach.com/sites/gacsach.com/files/styles/book310/public/nha-gia-kim.jpg?itok=NpTLrhhU', 'Tất cả những trải nghiệm trong chuyến phiêu du theo đuổi vận mệnh của mình đã giúp Santiago thấu hiểu được ý nghĩa sâu xa nhất của hạnh phúc, hòa hợp với vũ trụ và con người.\r\n\r\nTiểu thuyết Nhà giả kim của Paulo Coelho như một câu chuyện cổ tích giản dị, nhân ái, giàu chất thơ, thấm đẫm những minh triết huyền bí của phương Đông. Trong lần xuất bản đầu tiên tại Brazil vào năm 1988, sách chỉ bán được 900 bản. Nhưng, với số phận đặc biệt của cuốn sách dành cho toàn nhân loại, vượt ra ngoài biên giới quốc gia, Nhà giả kim đã làm rung động hàng triệu tâm hồn, trở thành một trong những cuốn sách bán chạy nhất mọi thời đại, và có thể làm thay đổi cuộc đời người đọc.', 1),
(7, 'Truyền kỳ mạn lục', 55000, 'https://a.wattpad.com/cover/117487109-352-k109309.jpg', 'Truyền kỳ mạn lục là tác phẩm văn xuôi duy nhất của Việt Nam từ xa xưa được đánh giá là “thiên cổ kỳ bút” (ngòi bút kỳ lạ của muôn đời), một cái mốc lớn của lịch sử văn học, sau này được dịch ra nhiều thứ tiếng trên thế giới. Tác phẩm gồm 20 truyện thông qua các nhân vật thần tiên, ma quái nhằm gửi gắm ý tưởng phê phán nền chính sự rối loạn, xã hội nhiễu nhương. Truyền kỳ mạn lục đã được dịch và giới thiệu ở một số nước như Pháp, Nga…\r\nGiá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Tuy nhiên tuỳ vào từng loại sản phẩm hoặc phương thức, địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, ...', 2),
(8, 'Harry Potter Ngoại Truyện ', 60000, 'https://cf.shopee.vn/file/1ecfc0e25a568ab3ee502df2d8307c2d', 'Những chuyện kể của Beedle Người Hát Rong gồm năm câu chuyện thần tiên với những phép thuật lạ lùng độc đáo, những tình huống căng thẳng hồi hộp sẽ làm say mê độc giả của mọi lứa tuổi. Đặc biệt sau mỗi câu chuyện có phần bình luận của giáo sư Albus Dumbledore. Với những suy nghĩ sâu sắc ý nhị và phần hé lộ thông tin về cuộc sống tại trường Hogwarts, những lời bàn của giáo sư hy vọng sẽ được dân Muggles và giới phù thủy yêu thích như nhau.\r\n\r\nSách có nhiều minh họa đẹp, rất được yêu thích trong giờ đọc sách trước khi ngủ ở các gia đình phù thủy nhiều thế kỷ qua.\r\n\r\nGiá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Tuy nhiên tuỳ vào từng loại sản phẩm hoặc phương thức, địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, ...', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaTaiKhoan'` int(11) NOT NULL,
  `taikhoan` varchar(200) NOT NULL,
  `matkhau` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaTaiKhoan'`, `taikhoan`, `matkhau`) VALUES
(1, 'thien@gmail.com', '123456789');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaTaiKhoan'`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `MaTaiKhoan'` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
