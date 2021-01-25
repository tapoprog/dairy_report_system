<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${employee != null}">
                <h2>
                    ${employee.name} の従業員情報　詳細ページ
                </h2>

                <table>
                    <tbody>
                        <tr>
                            <th>社員番号</th>
                            <td><c:out value="${employee.code}" /></td>
                        </tr>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>権限</th>
                            <td>
                                <c:choose>
                                    <c:when test="${employee.admin_flag == 1}">管理者</c:when>
                                    <c:otherwise>一般</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${employee.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${employee.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <c:choose>
                                    <c:when test="${employee.id == loginEmployee.id}">
                                        <a>ログイン中</a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test = "${follows != null}">
                                                <form method="POST" action="<c:url value='/follows/destroy' />">
                                                    <input type="hidden" name="_token" value="${_token}" />
                                                    <input type="hidden" name="follow_id" value="${follows.id}" />
                                                    <input type="hidden" name="employeeId" value="${employee.id}" />
                                                    <button type="submit" onclick="return confirm('フォローを外しますか？');">フォロー中</button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form method="POST" action="<c:url value='/follows/create' />">
                                                    <input type="hidden" name="employeeId" value="${employee.id}" />
                                                    <input type="hidden" name="_token" value="${_token}" />
                                                    <button type="submit">フォロー</button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </th>
                            <td>
                                <a href="<c:url value='/follows/index?id=${employee.id}' />">フォローリスト</a>
                                /<a href="<c:url value='/followers/index?id=${employee.id}' />">フォロワーリスト</a>
                            </td>
                        </tr>
                        <tr>
                        	<th><a href="<c:url value='/goods/index?id=${employee.id}' />">いいねリスト</a></th>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/employees/edit?id=${employee.id}' />">この従業員情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/employees/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>