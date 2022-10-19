
const options = document.querySelector(".options")
const donationButton = document.querySelector("input.donateButton")
const orgDonationButton = document.querySelector("input.orgDonateButton")

function quick() {
    options.classList.toggle("hide")
}

function buttonHide() {
    const sum = document.querySelector('input.donateSum').value;
    const checkList = document.querySelectorAll('input.donationCheck');
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
    }
}

function orgButtonHide() {
    const orgSum = document.querySelector('input.orgDonateSum').value;
    orgDonationButton.disabled = true

    if (orgSum > 0) {
        orgDonationButton.disabled = false
    }
}