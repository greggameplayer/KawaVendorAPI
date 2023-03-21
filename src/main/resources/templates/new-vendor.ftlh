<!DOCTYPE html>
<html lang="fr">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Libre+Baskerville&display=swap');

        body, table, td, a {
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
        }

        table, td {
            mso-table-lspace: 0;
            mso-table-rspace: 0;
        }

        img {
            -ms-interpolation-mode: bicubic;
        }

        img {
            border: 0;
            height: auto;
            line-height: 100%;
            outline: none;
            text-decoration: none;
        }

        table {
            border-collapse: collapse !important;
        }

        body {
            height: 100% !important;
            margin: 0 !important;
            padding: 0 !important;
            width: 100% !important;
        }

        a[x-apple-data-detectors] {
            color: inherit !important;
            text-decoration: none !important;
            font-size: inherit !important;
            font-family: inherit !important;
            font-weight: inherit !important;
            line-height: inherit !important;
        }

        div[style*="margin: 16px 0;"] {
            margin: 0 !important;
        }


        @media only screen and (max-width: 768px) {
            .main-container {
                width: 90%;
                min-width: 90%;
            }
        }


        @media only screen and (min-width: 768px) {
            .main-container {
                width: 85%;
                min-width: 85%;
            }
        }


        @media only screen and (min-width: 889px) {
            .main-container {
                width: 65%;
                min-width: 65%;
            }
        }

        /* Extra big screen devices (1200px and above) */
        @media only screen and (min-width: 1200px) {
            .main-container {
                width: 50%;
                min-width: 50%;
            }
        }

        .btn {
            align-items: center;
            appearance: none;
            background-image: radial-gradient(100% 100% at 100% 0, #C6B77A 0, #3E2F06 100%);
            background-color: #3E2F06;
            border: 0;
            border-radius: 6px;
            box-shadow: rgba(62, 47, 6, .4) 0 2px 4px, rgba(62, 47, 6, .3) 0 7px 13px -3px, rgba(4, 0, 0, .5) 0 -3px 0 inset;
            box-sizing: border-box;
            color: #fff !important;
            cursor: pointer;
            display: inline-flex;
            font-family: 'Libre Baskerville', serif;
            height: 48px;
            justify-content: center;
            line-height: 2.5;
            list-style: none;
            overflow: hidden;
            padding-left: 16px;
            padding-right: 16px;
            position: relative;
            text-align: left;
            text-decoration: none;
            transition: box-shadow .15s, transform .15s;
            user-select: none;
            -webkit-user-select: none;
            touch-action: manipulation;
            white-space: nowrap;
            will-change: box-shadow, transform;
            font-size: 18px;
        }

        .btn:focus {
            box-shadow: #3E2F06 0 0 0 1.5px inset, rgba(62, 47, 6, .4) 0 2px 4px, rgba(4, 0, 0, .5) 0 7px 13px -3px, rgba(4, 0, 0, .5) 0 -3px 0 inset;
        }

        .btn:hover {
            box-shadow: rgba(62, 47, 6, .4) 0 4px 8px, rgba(4, 0, 0, .5) 0 7px 13px -3px, rgba(4, 0, 0, .5) 0 -3px 0 inset;
            transform: translateY(-2px);
        }

        .btn:active {
            box-shadow: #3E2F06 0 3px 7px inset;
            transform: translateY(2px);
        }
    </style>
</head>
<body style="background-color: #f7f5fa; margin: 0 !important; padding: 0 !important;">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td bgcolor="#3E2F06" align="center">
            <table border="0" cellpadding="0" cellspacing="0" class="main-container">
                <tr>
                    <td align="center" valign="top" style="padding: 40px 10px 40px 10px;">
                        <div
                            style="display: block; font-family: Helvetica, Arial, sans-serif; color: #ffffff; font-size: 18px;"
                            border="0">PayeTonKawa
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td bgcolor="#3E2F06" align="center" style="padding: 0px 10px 0px 10px;">
            <table border="0" cellpadding="0" cellspacing="0" class="main-container">
                <tr>
                    <td bgcolor="#ffffff" align="left" valign="top"
                        style="padding: 30px 30px 20px 15px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;">
                        <h1 style="font-size: 32px; font-weight: 400; margin: 0;">Bonjour ${vendor}</h1>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td bgcolor="#f4f4f4" align="center" style="padding: 0px 10px 0px 10px;">
            <table border="0" cellpadding="0" cellspacing="0" class="main-container">
                <tr>
                    <td bgcolor="#ffffff" align="left">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td colspan="2"
                                    style="padding-left:15px;padding-right:15px;padding-bottom:10px; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px; display: flex; flex-direction: column;">
                                    <p style="justify-content: flex-start;">Bienvenue chez PayeTonKawa, vous pouvez dés
                                        à présent vous authentifiez grâce au QRCode ci-dessous :</p>
                                    <img src="data:image/png;base64,${qrCode}" alt=""
                                         style="align-self: center; width: 50%;">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"
                                    style="padding-left:15px;padding-right:15px;padding-bottom:10px; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 25px;">
                                    <p>Sinon vous pouvez aussi vous authentifiez grâce au bouton ci-dessous :</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#ffffff" align="center">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td bgcolor="#ffffff" align="center"
                                    style="padding: 30px 30px 30px 30px; border-top:1px solid #dddddd;">
                                    <table border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td align="left" style="border-radius: 3px;">
                                                <a class="btn"
                                                   href="https://kawamobilevisualizer.page.link/?link=https://kawamobilevisualizer.page.link/dashboardProduct?token=${token}&apn=com.kawa.kawa_mobile_visualizer">S'authentifier</a>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td bgcolor="#f4f4f4" align="center" style="padding: 0px 10px 0px 10px;">
            <table border="0" cellpadding="0" cellspacing="0" class="main-container">
                <tr>
                    <td bgcolor="#f4f4f4" align="left"
                        style="padding: 30px 30px 30px 30px; color: #666666; font-family: Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;">
                        <p style="margin: 0;">&copy; 2023 PayeTonKawa</p>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
