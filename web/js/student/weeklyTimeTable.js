document.addEventListener("DOMContentLoaded", function () {

    const yearSelect = document.getElementById('year');
    const weekSelect = document.getElementById('week');
    const dateElements = document.querySelectorAll('.date');
    let datePairs = []; // Mảng lưu trữ cặp ngày "dd/mm" cho mỗi tuần

// Lấy tuần hiện tại
    function getCurrentWeekNumber() {
        const currentDate = new Date();
        currentDate.setHours(0, 0, 0, 0);
        currentDate.setDate(currentDate.getDate() + 3 - (currentDate.getDay() + 6) % 7);
        const startOfYear = new Date(currentDate.getFullYear(), 0, 1);
        const weekNumber = Math.ceil(((currentDate - startOfYear) / 86400000 + 1) / 7);
        return weekNumber;
    }

// Tạo một hàm để cập nhật thẻ "week" và các thẻ "date" dựa trên năm được chọn và tuần được chọn
    function updateContent() {
        const selectedYear = yearSelect.value; // Lấy năm được chọn

        // Xóa tất cả các tùy chọn hiện có trong thẻ "week"
        weekSelect.innerHTML = '';

        // Tạo một mảng các cặp ngày "dd/mm" theo yêu cầu
        datePairs = [];
        let currentDate = new Date(selectedYear, 0, 1); // Ngày đầu tiên của năm
        while (currentDate.getDay() !== 1) {
            currentDate.setDate(currentDate.getDate() + 1);
        }
        while (currentDate.getFullYear() == selectedYear) {
            const startOfWeek = new Date(currentDate);
            const endOfWeek = new Date(currentDate);
            endOfWeek.setDate(currentDate.getDate() + 6);
            datePairs.push({
                start: startOfWeek.toLocaleDateString('en-GB').substring(0, 5), // Lấy "dd/mm" cho ngày thứ 2
                end: endOfWeek.toLocaleDateString('en-GB').substring(0, 5), // Lấy "dd/mm" cho ngày chủ nhật
            });
            currentDate.setDate(currentDate.getDate() + 7);
        }

        // Thêm các tùy chọn vào thẻ "week" với định dạng "dd/mm To dd/mm"
        datePairs.forEach((datePair, index) => {
            const option = document.createElement('option');
            option.value = index + 1; // Giá trị tuần bắt đầu từ 1
            option.text = `${datePair.start} To ${datePair.end}`;
            weekSelect.appendChild(option);
        });

        // Chọn tuần hiện tại
        weekSelect.value = getCurrentWeekNumber();

        // Cập nhật các thẻ có class "date" theo định dạng "dd/mm" cho tuần hiện tại
        updateDateElements();
    }

// Tạo một hàm để cập nhật các thẻ "date" dựa trên tuần được chọn
    function updateDateElements() {
        const selectedIndex = weekSelect.selectedIndex;
        if (selectedIndex >= 0) {
            const selectedDatePair = datePairs[selectedIndex];
            const mondayDate = selectedDatePair.start; // Lấy ngày thứ hai của tuần đã chọn

            dateElements.forEach((dateElement, index) => {
                dateElement.textContent = getDateForIndex(mondayDate, index);
            });
        }
    }

// Hàm để lấy ngày cho mỗi thẻ <td> dựa trên ngày thứ hai và chỉ số index
    function getDateForIndex(mondayDate, index) {
        const [day, month] = mondayDate.split('/');
        const currentDate = new Date(`${month}/${day}/${yearSelect.value}`); // Chuyển đổi ngày/tháng thành ngày/tháng/năm
        currentDate.setDate(currentDate.getDate() + index); // Lấy ngày cho thẻ <td> thứ index
        const newDay = currentDate.getDate();
        const newMonth = currentDate.getMonth() + 1; // Lưu ý: getMonth() trả về tháng từ 0 đến 11, cần +1
        return `${newDay.toString().padStart(2, '0')}/${newMonth.toString().padStart(2, '0')}`; // Định dạng lại ngày/tháng
    }

// Sự kiện lắng nghe khi tuần thay đổi
    weekSelect.addEventListener('change', updateDateElements);

// Gọi hàm cập nhật khi năm thay đổi
    yearSelect.addEventListener('change', updateContent);

// Khởi đầu ban đầu
    updateContent();
});

function submitForm(option) {
    var year = document.querySelector('#year').value;
    var selectedIndex = option.selectedIndex;
    if (selectedIndex !== -1) {
        var selectedText = option.options[selectedIndex].text;
        var parts = selectedText.split(" To ");

        if (parts.length === 2) {
            // Tách ngày và tháng từ mỗi phần tử con
            var startDate = parts[0].split("/");
            var endDate = parts[1].split("/");

            // Đảo ngày và tháng
            var formattedStartDate = year + "-" + endDate[1] + "-" + startDate[0];
            var monday = document.querySelector('#monday');
            monday.value = formattedStartDate;
            document.querySelector('#form').submit();
        }
    }
}