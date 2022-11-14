var dataSet = [
    {
        pName: '페이지 네이션1', // 프로젝트 이름
        name: '임나나', // 작성자
        date: '8월 4일'
    },
    {
        pName: '페이지 네이션2', // 프로젝트 이름
        name: '임누누', // 작성자
        date: '8월 4일 1초'
    },
    {
        pName: '페이지 네이션3', // 프로젝트 이름
        name: '임노노', // 작성자
        date: '8월 4일 2초'
    },
    {
        pName: '페이지 네이션4', // 프로젝트 이름
        name: '임부각', // 작성자
        date: '8월 4일 3초'
    },
    {
        pName: '페이지 네이션5', // 프로젝트 이름
        name: '임도도', // 작성자
        date: '8월 4일 4초'
    },
    {
        pName: '페이지 네이션6', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션7', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션8', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션9', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션10', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션11', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션12', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션13', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션14', // 프로젝트 이름
        name: '임수수', // 작성자
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    },
    {
        pName: '페이지 네이션15',
        name: '임수수', 
        date: '8월 4일 5초'
    }
];
const displayPageNav = perPage => {

    let pagination = ``
    const totalItems = dataSet.length
    perPage = perPage ? perPage : 1
    const pages = Math.ceil(totalItems / perPage)

    for (let i = 1; i <= pages; i++) {
        pagination += `<li class="page-item"><a class="page-link" href="#" onClick="displayItems(${i},${perPage})" >${i}</a></li>`
    }

    document.getElementById('pagination').innerHTML = pagination

}

const displayItems = (page = 1, perPage = 2) => {

    let index, offSet

    if (page == 1 || page <= 0) {
        index = 0
        offSet = perPage
    } else if (page > dataSet.length) {
        index = page - 1
        offSet = dataSet.length
    } else {
        index = page * perPage - perPage
        offSet = index + perPage
    }

    const slicedItems = dataSet.slice(index, offSet)
    let today = new Date();

    let time = today.getFullYear()+'.'+(today.getMonth()+1) + '.' +today.getDate()

    const html = slicedItems.map(item =>
        `
     <ul class="border p-3">
         <li><a href="../view/read.html">${item.pName}</a></li>
          <li>
              <span>${item.name}</span>
              <span>${time}</span>
        </li>
      </ul>
    `
    )

    document.querySelector('.board').innerHTML = html.join('')

}

let perPage = 6
displayPageNav(perPage)
displayItems(1, perPage)