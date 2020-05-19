import moment from 'moment'

const formatDataTime = time => {
  if (time) {
    return moment(time).format('YYYY-MM-DD HH:mm:ss')
  } else {
    return ''
  }
}

const formatGender = gender => {
  if (gender === 0) {
    return '女'
  } else if (gender === 1) {
    return '男'
  } else {
    return ''
  }
}

const formatBalance = balance => {
  return parseFloat(balance).toFixed(2)
}

export default {
  formatDataTime,
  formatGender,
  formatBalance
}
