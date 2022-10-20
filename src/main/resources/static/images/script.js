
function quick() {
    const options = document.querySelector(".options")
    options.classList.toggle("hide")
}

function buttonHide() {
    const sum = document.querySelector('input.donateSum').value;
    const checkList = document.querySelectorAll('input.donationCheck');
    const donationButton = document.querySelector('input.donateButton')
    donationButton.classList.remove("donateButtonActive")
    donationButton.classList.remove("rounded-1")
    donationButton.disabled = true
    let checkMark = false

    for (let i = 0; i < checkList.length; i++) {
        if (checkList[i].checked) {
            checkMark = true
            break
        }
    }

    if (sum > 0 && checkMark) {
        donationButton.disabled = false
        donationButton.classList.add("donateButtonActive")
        donationButton.classList.add("rounded-1")

    }
}

function orgButtonHide() {
    const orgSumList = document.querySelectorAll('input.orgDonateSum');
    const orgButtonList = document.querySelectorAll('input.orgDonateButton')

    console.log(orgButtonList)
    for (let i = 0; i < orgSumList.length; i++) {
        orgButtonList[i].disabled = true

        if (orgSumList[i].value > 0) {
                orgButtonList[i].disabled = false
            }
    }
}