// function IdCheck() {
//     const getid = document.getElementById("memberId").value;
//     const checkResult = document.getElementById("check-result");
//     console.log("입력값: ", getid);
//     $.ajax({
//         type: "post",
//         url: "/member/Id-check",
//         data: {
//             "memberId": getid
//         },
//         success: function(res) {
//             console.log("요청성공", res);
//             if (res == "ok") {
//                 console.log("사용가능한 아이디");
//                 checkResult.style.color = "green";
//                 checkResult.innerHTML = "사용가능한 아이디";
//             } else {
//                 console.log("이미 사용중인 아이디");
//                 checkResult.style.color = "red";
//                 checkResult.innerHTML = "이미 사용중인 아이디";
//             }
//         },
//         error: function(err) {
//             console.log("에러발생", err);
//         }
//     });
// }